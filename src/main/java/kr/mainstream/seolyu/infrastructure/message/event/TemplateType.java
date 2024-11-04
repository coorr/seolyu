package kr.mainstream.seolyu.infrastructure.message.event;

import kr.mainstream.seolyu.infrastructure.message.event.parameter.EventApplicantCreateTemplateParameter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TemplateType {
    EVENT_APPLICANT_CREATED(EventApplicantCreateTemplateParameter.class);

    private final Class<?> getParameterClass;
}
