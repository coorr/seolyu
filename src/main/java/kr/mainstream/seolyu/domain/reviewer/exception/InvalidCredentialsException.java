package kr.mainstream.seolyu.domain.reviewer.exception;

import kr.mainstream.seolyu.common.exception.SimpleMessageIllegalStateException;

public class InvalidCredentialsException extends SimpleMessageIllegalStateException {
    public InvalidCredentialsException() {
        super("아이디 또는 패스워드가 일치하지 않습니다.");
    }
}
