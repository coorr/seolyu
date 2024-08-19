package kr.mainstream.seolyu.domain.resumeReview.exception;

import kr.mainstream.seolyu.common.exception.SimpleMessageIllegalArgumentException;

public class RequiredFeedbackDataMissingException extends SimpleMessageIllegalArgumentException {
    public RequiredFeedbackDataMissingException() {
        super("필수 피드백 정보가 누락되었습니다. 요약, 의견, 첫 번째 피드백 제목 및 내용을 확인해 주세요.");
    }
}
