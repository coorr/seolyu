package kr.mainstream.seolyu.infrastructure.message.event.builder;

import kr.mainstream.seolyu.infrastructure.message.event.TemplateType;

public class EventMessageBuilderFactory {
    public EventApplicantMessageBuilder createBuilder(TemplateType templateType) {
        return new EventApplicantMessageBuilder(templateType);
    }
}
