package kr.mainstream.seolyu.domain.resumeReview.status;

import kr.mainstream.seolyu.domain.resumeReview.exception.CanNotChangeStatusException;
import org.springframework.stereotype.Component;

@Component
public class ResumeReviewReadyHandler implements ResumeReviewStatusHandler{

    @Override
    public void handle(String id) {
        throw new CanNotChangeStatusException();
    }
}
