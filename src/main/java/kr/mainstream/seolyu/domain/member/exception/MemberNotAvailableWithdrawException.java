package kr.mainstream.seolyu.domain.member.exception;


import kr.mainstream.seolyu.common.exception.SimpleMessageIllegalStateException;

public class MemberNotAvailableWithdrawException extends SimpleMessageIllegalStateException {
    public MemberNotAvailableWithdrawException() {
        super("탈퇴한 회원은 이용하실 수 없습니다.");
    }
}
