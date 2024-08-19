package kr.mainstream.seolyu.domain.resumeReview.validator;

import kr.mainstream.seolyu.domain.resumeReview.status.ResumeReviewStatus;
import kr.mainstream.seolyu.domain.resumeReview.dto.ResumeReviewPatchReqDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ResumeReviewPatchReqDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ResumeReviewPatchReqDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ResumeReviewPatchReqDto dto = (ResumeReviewPatchReqDto) o;

        try {
            ResumeReviewStatus.valueOf(dto.getStatus());
        } catch (Exception e) {
            errors.rejectValue("status", null, "유효하지 않는 상태입니다.");
        }
    }
}
