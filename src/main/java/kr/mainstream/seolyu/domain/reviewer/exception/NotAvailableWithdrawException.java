package kr.mainstream.seolyu.domain.reviewer.exception;


import kr.mainstream.seolyu.common.exception.SimpleMessageIllegalStateException;

public class NotAvailableWithdrawException extends SimpleMessageIllegalStateException {
    public NotAvailableWithdrawException() {
        super("탈퇴한 회원은 이용하실 수 없습니다.");
    }
}
