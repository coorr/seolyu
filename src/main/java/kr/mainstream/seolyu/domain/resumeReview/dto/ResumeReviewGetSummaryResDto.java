package kr.mainstream.seolyu.domain.resumeReview.dto;

import kr.mainstream.seolyu.domain.resumeReview.JobPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResumeReviewGetSummaryResDto {
    private String id;
    private String resumeDetails;
    private JobPosition position;
    private String httpUrl;
    private String fileUrl;
    private String name;
    private String email;
    private Long resumeFeedbackId;
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
