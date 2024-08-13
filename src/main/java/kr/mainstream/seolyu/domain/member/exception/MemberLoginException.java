package kr.mainstream.seolyu.domain.member.exception;


import kr.mainstream.seolyu.common.exception.SimpleMessageIllegalStateException;

public class MemberLoginException extends SimpleMessageIllegalStateException {
    public MemberLoginException() {
        super("로그인을 실패하였습니다.");
    }
}
