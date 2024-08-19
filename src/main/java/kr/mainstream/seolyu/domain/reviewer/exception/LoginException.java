package kr.mainstream.seolyu.domain.reviewer.exception;


import kr.mainstream.seolyu.common.exception.SimpleMessageIllegalStateException;

public class LoginException extends SimpleMessageIllegalStateException {
    public LoginException() {
        super("로그인을 실패하였습니다.");
    }
}
