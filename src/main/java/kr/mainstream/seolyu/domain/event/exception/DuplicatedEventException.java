package kr.mainstream.seolyu.domain.event.exception;

import kr.mainstream.seolyu.common.exception.SimpleMessageIllegalStateException;

public class DuplicatedEventException extends SimpleMessageIllegalStateException {
    public DuplicatedEventException() {
        super("이미 발급 요청이 처리됐습니다.");
    }
}
