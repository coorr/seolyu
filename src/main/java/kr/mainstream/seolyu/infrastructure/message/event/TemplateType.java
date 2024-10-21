package kr.mainstream.seolyu.infrastructure.message.event;

import kr.mainstream.seolyu.infrastructure.message.event.template.EventApplicantCreateMessageTemplate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TemplateType {
    EVENT_APPLICANT_CREATED(EventApplicantCreateMessageTemplate.class);

    private final Class<?> templateClass;
}
