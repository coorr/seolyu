package kr.mainstream.seolyu.domain.applicant;

import jakarta.validation.Valid;
import kr.mainstream.seolyu.common.exception.ValidationIllegalArgumentException;
import kr.mainstream.seolyu.common.exception.file.EmptyFileException;
import kr.mainstream.seolyu.common.exception.file.FileSizeExceededException;
import kr.mainstream.seolyu.common.exception.file.NotSupportedFileTypeException;
import kr.mainstream.seolyu.domain.applicant.dto.ApplicantCreateReqDto;
import kr.mainstream.seolyu.domain.applicant.dto.ApplicantPostReqDto;
import kr.mainstream.seolyu.domain.applicant.validator.ApplicantPostReqDtoValidator;
import kr.mainstream.seolyu.infrastructure.email.EmailMessageBuilder;
import kr.mainstream.seolyu.infrastructure.email.EmailMessageSender;
import kr.mainstream.seolyu.infrastructure.email.template.RequestCompleteEmailTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;

import static kr.mainstream.seolyu.domain.applicant.ApplicantProperties.MAX_FILE_SIZE;
import static kr.mainstream.seolyu.domain.applicant.ApplicantProperties.PDF_EXTENSION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/applicants")
@Slf4j
public class ApplicantController {
    private final ApplicantService applicantService;
    private final ApplicantPostReqDtoValidator applicantPostReqDtoValidator;
    private final EmailMessageBuilder emailMessageBuilder;
    private final EmailMessageSender emailMessageSender;

    @PostMapping
    public ResponseEntity<Void> post(
//            @RequestPart(value = "file", required = false) MultipartFile file,
                                     @Valid @RequestPart("applicantPostReqDto") ApplicantPostReqDto dto,
                                     BindingResult bindingResult) {
        applicantPostReqDtoValidator.validate(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ValidationIllegalArgumentException(bindingResult);
        }
        MultipartFile file = null;
        try {
            byte[] content = "This is a mock file content".getBytes(); // 파일 내용
            InputStream inputStream = new ByteArrayInputStream(content);
            file = new MockMultipartFile("file", "mockFile.txt", "text/plain", inputStream); // MockMultipartFile 생성
        } catch (Exception e) {
            log.error("MockMultipartFile 에러 발생");
        }
//        validation(file);
        applicantService.save(new ApplicantCreateReqDto(dto, file), LocalDateTime.now());
        emailMessageSender.send(emailMessageBuilder.build(dto.getEmail(), RequestCompleteEmailTemplate.create(dto.getName())));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

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
}
