package kr.mainstream.seolyu.domain.resumeReview.status;

import kr.mainstream.seolyu.domain.resumeReview.status.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static kr.mainstream.seolyu.domain.resumeReview.status.ResumeReviewStatus.*;

@Component
public class ResumeReviewStatusFactory {
    private final Map<ResumeReviewStatus, ResumeReviewStatusHandler> map = new HashMap<>();

    public ResumeReviewStatusFactory(ResumeReviewReadyHandler resumeReviewReadyHandler,
                                     ResumeReviewCompletedHandler resumeReviewCompletedHandler,
                                     ResumeReviewCancelHandler resumeReviewCancelHandler) {
        map.put(READY, resumeReviewReadyHandler);
        map.put(COMPLETED, resumeReviewCompletedHandler);
        map.put(CANCEL, resumeReviewCancelHandler);
    }

    public ResumeReviewStatusHandler get(ResumeReviewStatus status) {
        return map.get(status);
    }
}
