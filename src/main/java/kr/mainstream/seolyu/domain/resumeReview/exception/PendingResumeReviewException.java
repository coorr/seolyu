package kr.mainstream.seolyu.domain.resumeReview.exception;

import kr.mainstream.seolyu.common.exception.SimpleMessageIllegalStateException;

public class PendingResumeReviewException extends SimpleMessageIllegalStateException {
    public PendingResumeReviewException() {
        super("이미 검토되지 않은 서류가 존재합니다. 검토가 완료된 후 다시 신청해 주세요.");
    }
}
