package kr.mainstream.seolyu.domain.event.exception;

import kr.mainstream.seolyu.common.exception.SimpleMessageIllegalStateException;

public class EventPeriodException extends SimpleMessageIllegalStateException {
    public EventPeriodException() {
        super("이벤트 기간이 아닙니다.");
    }
}
