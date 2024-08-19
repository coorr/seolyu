package kr.mainstream.seolyu.domain.applicant.exception;

import kr.mainstream.seolyu.common.exception.NotFoundException;

public class ApplicantNotFoundException extends NotFoundException {
    public ApplicantNotFoundException() {
        super("요청하신 지원자를 찾을 수 없습니다. 다시 확인해 주세요.");
    }
}
