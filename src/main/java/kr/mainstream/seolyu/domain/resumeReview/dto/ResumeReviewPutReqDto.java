package kr.mainstream.seolyu.domain.resumeReview.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumeReviewPutReqDto {
    private String initialFeedbackTitle;
    private String initialFeedbackContent;
    private String midtermFeedbackTitle;
    private String midtermFeedbackContent;
    private String finalFeedbackTitle;
    private String finalFeedbackContent;
    private String additionalFeedbackTitle;
    private String additionalFeedbackContent;
    private String feedbackSummary;
    private String comment;
}
