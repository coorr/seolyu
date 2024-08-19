package kr.mainstream.seolyu.domain.resumeReview.exception;

import kr.mainstream.seolyu.common.exception.SimpleMessageIllegalStateException;

public class CanNotChangeStatusException extends SimpleMessageIllegalStateException {
    public CanNotChangeStatusException() {
        super("변경할 수 없는 상태입니다.");
    }
}
