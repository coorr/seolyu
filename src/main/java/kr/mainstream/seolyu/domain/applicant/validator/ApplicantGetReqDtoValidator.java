package kr.mainstream.seolyu.domain.applicant.validator;

import kr.mainstream.seolyu.domain.applicant.dto.ApplicantGetReqDto;
import kr.mainstream.seolyu.domain.resumeReview.status.ResumeReviewStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ApplicantGetReqDtoValidator implements Validator {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public boolean supports(Class<?> clazz) {
        return ApplicantGetReqDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ApplicantGetReqDto dto = (ApplicantGetReqDto) o;
        if (StringUtils.hasText(dto.getStartedDate())) {
            validationDate(dto.getStartedDate(), "startedDate", errors);
        }

        if (StringUtils.hasText(dto.getEndedDate())) {
            validationDate(dto.getEndedDate(), "endedDate", errors);
        }

        if (StringUtils.hasText(dto.getStartedDate()) && StringUtils.hasText(dto.getEndedDate())) {
            LocalDate start = LocalDate.parse(dto.getStartedDate(), formatter);
            LocalDate end = LocalDate.parse(dto.getEndedDate(), formatter);
            if (start.isAfter(end)) {
                errors.rejectValue("startedDate", null, "시작일이 종료일보다 미래가 될 수 없습니다.");
            }
        }

        if (StringUtils.hasText(dto.getStatus())) {
            try {
                ResumeReviewStatus.valueOf(dto.getStatus());
            } catch (Exception e) {
                errors.rejectValue("status", null, "유효하지 않는 검토 상태 검색 조건 입니다.");
            }
        }
    }

    private void validationDate(String value, String field, Errors errors) {
        try {
            LocalDate.parse(value, formatter);
        } catch (Exception ex) {
            errors.rejectValue(field, null, "날짜 형식이 올바르지 않습니다.");
        }
    }
}
