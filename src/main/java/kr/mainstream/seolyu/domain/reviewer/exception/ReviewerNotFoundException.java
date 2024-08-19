package kr.mainstream.seolyu.domain.reviewer.exception;


import kr.mainstream.seolyu.common.exception.NotFoundException;

public class ReviewerNotFoundException extends NotFoundException {
    public ReviewerNotFoundException() {
        super("일치하는 회원정보가 없습니다. 정보를 확인 후 다시 시도해주세요.");
    }
}
