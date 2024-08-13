package kr.mainstream.seolyu.domain.resumeReview;

import kr.mainstream.seolyu.common.exception.ValidationIllegalArgumentException;
import kr.mainstream.seolyu.domain.resumeReview.dto.ResumeReviewGetReqDto;
import kr.mainstream.seolyu.domain.resumeReview.dto.ResumeReviewGetSummaryResDto;
import kr.mainstream.seolyu.domain.resumeReview.dto.ResumeReviewSearchReqDto;
import kr.mainstream.seolyu.domain.resumeReview.dto.ResumeReviewSearchResDto;
import kr.mainstream.seolyu.domain.resumeReview.validator.ResumeReviewGetReqDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/resume-reviews")
public class ResumeReviewAdminController {
    private final ResumeReviewAdminService resumeReviewAdminService;
    private final ResumeReviewGetReqDtoValidator resumeReviewGetReqDtoValidator;

    @GetMapping
    public ResponseEntity<Page<ResumeReviewSearchResDto>> getList(ResumeReviewGetReqDto dto, BindingResult bindingResult) {
        resumeReviewGetReqDtoValidator.validate(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ValidationIllegalArgumentException(bindingResult);
        }
        ResumeReviewSearchReqDto reqDto = new ResumeReviewSearchReqDto(dto);
        return ResponseEntity.ok(resumeReviewAdminService.getList(reqDto));
    }

    @GetMapping("/{id}/summary")
    public ResponseEntity<ResumeReviewGetSummaryResDto> getSummary(@PathVariable String id) {
        return ResponseEntity.ok(resumeReviewAdminService.getSummary(id));
    }
}
