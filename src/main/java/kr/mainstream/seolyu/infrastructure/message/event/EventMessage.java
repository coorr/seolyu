package kr.mainstream.seolyu.infrastructure.message.event;

import kr.mainstream.seolyu.infrastructure.message.AbstractMessage;
import lombok.Getter;

@Getter
public class EventMessage extends AbstractMessage<EventMessageTemplate> {
    public EventMessage(MessageType messageType, EventMessageTemplate message) {
        super(messageType, message);
    }
}
