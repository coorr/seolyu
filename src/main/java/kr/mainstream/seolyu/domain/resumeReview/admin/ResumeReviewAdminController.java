package kr.mainstream.seolyu.domain.resumeReview.admin;

import kr.mainstream.seolyu.common.exception.ValidationIllegalArgumentException;
import kr.mainstream.seolyu.domain.resumeReview.status.ResumeReviewStatus;
import kr.mainstream.seolyu.domain.resumeReview.dto.ResumeReviewGetDetailResDto;
import kr.mainstream.seolyu.domain.resumeReview.dto.ResumeReviewPatchReqDto;
import kr.mainstream.seolyu.domain.resumeReview.dto.ResumeReviewPutReqDto;
import kr.mainstream.seolyu.domain.resumeReview.exception.ResumeReviewNotFoundException;
import kr.mainstream.seolyu.domain.resumeReview.validator.ResumeReviewPatchReqDtoValidator;
import kr.mainstream.seolyu.login.LoginInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/resume-reviews")
public class ResumeReviewAdminController {
    private final ResumeReviewAdminService resumeReviewAdminService;
    private final ResumeReviewPatchReqDtoValidator resumeReviewPatchReqDtoValidator;

    @GetMapping("/{id}")
    public ResponseEntity<ResumeReviewGetDetailResDto> get(@PathVariable String id) {
        if (null == id) {
            throw new ResumeReviewNotFoundException();
        }
        return ResponseEntity.ok(resumeReviewAdminService.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> put(@PathVariable String id,
                                    @RequestBody ResumeReviewPutReqDto dto,
                                    @RequestAttribute LoginInfo loginInfo) {
        if (null == id) {
            throw new ResumeReviewNotFoundException();
        }
        resumeReviewAdminService.put(id, dto, loginInfo.getReviewerId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> patchStatus(@PathVariable String id,
                                            @RequestBody ResumeReviewPatchReqDto dto,
                                            BindingResult bindingResult) {

        if (null == id) {
            throw new ResumeReviewNotFoundException();
        }
        resumeReviewPatchReqDtoValidator.validate(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ValidationIllegalArgumentException(bindingResult);
        }
        resumeReviewAdminService.updateStatusWithHandle(id, ResumeReviewStatus.valueOf(dto.getStatus()));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
