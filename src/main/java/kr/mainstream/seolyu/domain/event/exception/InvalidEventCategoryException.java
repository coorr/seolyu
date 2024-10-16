package kr.mainstream.seolyu.domain.event.exception;

import kr.mainstream.seolyu.common.exception.SimpleMessageIllegalArgumentException;

public class InvalidEventCategoryException extends SimpleMessageIllegalArgumentException {
    public InvalidEventCategoryException() {
        super("유효하지 않는 유형입니다.");
    }
}
