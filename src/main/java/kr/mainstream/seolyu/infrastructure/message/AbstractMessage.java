package kr.mainstream.seolyu.infrastructure.message;

import kr.mainstream.seolyu.infrastructure.message.event.MessageType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractMessage<T> implements Message {
    private final MessageType messageType;
    private final T message;

    @Override
    public MessageType getType() {
        return messageType;
    }

    @Override
    public Object getMessage() {
        return message;
    }
}
