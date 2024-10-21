package kr.mainstream.seolyu.infrastructure.message;

import kr.mainstream.seolyu.infrastructure.message.event.MessageType;

public interface Message {
    MessageType getType();
    Object getMessage();
}
