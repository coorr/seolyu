package kr.mainstream.seolyu.domain.resumeReview.validator;

import kr.mainstream.seolyu.common.validator.EmailValidator;
import kr.mainstream.seolyu.common.validator.HttpUrlValidator;
import kr.mainstream.seolyu.domain.resumeReview.JobPosition;
import kr.mainstream.seolyu.domain.resumeReview.dto.ResumeReviewPostReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class ResumeReviewPostReqDtoValidator implements Validator {
    private final EmailValidator emailValidator;
    private final HttpUrlValidator httpUrlValidator;


    @Override
    public boolean supports(Class<?> clazz) {
        return ResumeReviewPostReqDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ResumeReviewPostReqDto dto = (ResumeReviewPostReqDto) o;

        try {
            JobPosition.valueOf(dto.getPosition());
        } catch (Exception e) {
            errors.rejectValue("position", null, "올바르지 않는 포지션입니다.");
        }

        if (dto.getRequestDetails().length() > 1001) {
            errors.rejectValue("requestDetails", null, "요청사항은 최대 1000자까지 입력 가능합니다.");
        }

        if (StringUtils.hasText(dto.getHttpUrl())) {
            ValidationUtils.invokeValidator(httpUrlValidator, dto.getHttpUrl(), errors);
        }

        ValidationUtils.invokeValidator(emailValidator, dto.getEmail(), errors);
    }
}
