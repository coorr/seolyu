package kr.mainstream.seolyu.domain.resumeReview;


import kr.mainstream.seolyu.common.container.LongIdentifier;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResumeReviewIdentifier extends LongIdentifier {

    public ResumeReviewIdentifier(String idStr) {
        super(idStr);
    }
}
