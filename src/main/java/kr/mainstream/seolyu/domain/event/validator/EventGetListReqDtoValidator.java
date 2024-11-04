package kr.mainstream.seolyu.domain.event.validator;

import kr.mainstream.seolyu.domain.event.EventCategory;
import kr.mainstream.seolyu.domain.event.dto.EventGetListReqDto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EventGetListReqDtoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return EventGetListReqDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EventGetListReqDto dto = (EventGetListReqDto) target;

        if (StringUtils.hasText(dto.getCategory())) {
            try {
                EventCategory.valueOf(dto.getCategory());
            } catch (Exception e) {
                errors.rejectValue("category", null, "유효하지 않는 유형입니다.");
            }
        }
    }
}
