package kr.mainstream.seolyu.domain.event.exception;

import kr.mainstream.seolyu.common.exception.NotFoundException;

public class NotFoundEventException extends NotFoundException {
    public NotFoundEventException() {
        super("존재하지 않는 이벤트입니다.");
    }
}
