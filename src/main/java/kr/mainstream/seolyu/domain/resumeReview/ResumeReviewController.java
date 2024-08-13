package kr.mainstream.seolyu.domain.resumeReview;

import jakarta.validation.Valid;
import kr.mainstream.seolyu.common.exception.ValidationIllegalArgumentException;
import kr.mainstream.seolyu.common.exception.file.EmptyFileException;
import kr.mainstream.seolyu.common.exception.file.FileSizeExceededException;
import kr.mainstream.seolyu.common.exception.file.NotSupportedFileTypeException;
import kr.mainstream.seolyu.domain.resumeReview.dto.ResumeReviewCreateReqDto;
import kr.mainstream.seolyu.domain.resumeReview.dto.ResumeReviewPostReqDto;
import kr.mainstream.seolyu.domain.resumeReview.validator.ResumeReviewPostReqDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resume-reviews")
public class ResumeReviewController {
    private static final String PDF_EXTENSION = ".pdf";
    private static final long MAX_FILE_SIZE = 20 * 1024 * 1024; // 20MB
    private final ResumeReviewPostReqDtoValidator resumeReviewPostReqDtoValidator;
    private final ResumeReviewService resumeReviewService;

    private void validation(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new EmptyFileException();
        }

        if (!file.getOriginalFilename().toLowerCase().endsWith(PDF_EXTENSION)) {
            throw new NotSupportedFileTypeException(PDF_EXTENSION);
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new FileSizeExceededException();
        }
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestPart(value = "file", required = false) MultipartFile file,
                                     @Valid @RequestPart("resumeReviewPostReqDto") ResumeReviewPostReqDto dto,
                                     BindingResult bindingResult) {
        resumeReviewPostReqDtoValidator.validate(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ValidationIllegalArgumentException(bindingResult);
        }
        validation(file);
        resumeReviewService.post(new ResumeReviewCreateReqDto(dto, file));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
