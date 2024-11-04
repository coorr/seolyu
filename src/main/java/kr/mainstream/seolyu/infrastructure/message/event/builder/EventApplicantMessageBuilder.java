package kr.mainstream.seolyu.infrastructure.message.event.builder;

import kr.mainstream.seolyu.infrastructure.message.Message;
import kr.mainstream.seolyu.infrastructure.message.event.EventMessage;
import kr.mainstream.seolyu.infrastructure.message.event.EventMessageTemplate;
import kr.mainstream.seolyu.infrastructure.message.event.MessageType;
import kr.mainstream.seolyu.infrastructure.message.event.TemplateType;

public class EventApplicantMessageBuilder extends EventMessageBuilderHelper<EventApplicantMessageBuilder> {
    private final MessageType type = MessageType.EVENT_APPLICANT;
    private final TemplateType templateType;

    public EventApplicantMessageBuilder(TemplateType templateType) {
        this.templateType = templateType;
    }

    @Override
    public Message build() {
        EventMessageTemplate eventMessageTemplate = new EventMessageTemplate(getDrill(), getParameter(), templateType.name());
        return new EventMessage(type, eventMessageTemplate);
    }
}
