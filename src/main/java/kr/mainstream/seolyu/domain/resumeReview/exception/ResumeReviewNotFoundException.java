package kr.mainstream.seolyu.domain.resumeReview.exception;

import kr.mainstream.seolyu.common.exception.NotFoundException;

public class ResumeReviewNotFoundException extends NotFoundException {
    public ResumeReviewNotFoundException() {
        super("서류 검토 신청 이력을 찾을 수 없습니다.");
    }
}
