package kr.mainstream.seolyu.domain.resumeReview.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResumeReviewGetResDto {
    private String id;
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
    private String reviewerBiography;
    private String applicantName;
}
