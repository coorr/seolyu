package kr.mainstream.seolyu.domain.event.exception;

import kr.mainstream.seolyu.common.exception.SimpleMessageIllegalArgumentException;

public class EventQuantityException extends SimpleMessageIllegalArgumentException {
    public EventQuantityException() {
        super("발급 가능한 수량을 초과합니다.");
    }
}
