package kr.mainstream.seolyu.domain.resumeReview.dto;

import kr.mainstream.seolyu.domain.applicant.definition.JobPosition;
import kr.mainstream.seolyu.domain.resumeReview.status.ResumeReviewStatus;
import lombok.Getter;

@Getter
public class ResumeReviewGetDetailResDto {
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
    private String status;

    private String reviewerName;
    private String reviewerBiography;

    private Long applicantId;
    private String requestDetails;
    private JobPosition position;
    private String resumeUrl;
    private String resumeFile;
    private String name;
    private String email;

    public ResumeReviewGetDetailResDto(String id, String initialFeedbackTitle, String initialFeedbackContent, String midtermFeedbackTitle,
                                       String midtermFeedbackContent, String finalFeedbackTitle, String finalFeedbackContent,
                                       String additionalFeedbackTitle, String additionalFeedbackContent, String feedbackSummary,
                                       String comment, ResumeReviewStatus status, String reviewerName, String reviewerBiography, Long applicantId,
                                       String requestDetails, JobPosition position, String resumeUrl, String resumeFile, String name, String email) {
        this.id = id;
        this.initialFeedbackTitle = initialFeedbackTitle;
        this.initialFeedbackContent = initialFeedbackContent;
        this.midtermFeedbackTitle = midtermFeedbackTitle;
        this.midtermFeedbackContent = midtermFeedbackContent;
        this.finalFeedbackTitle = finalFeedbackTitle;
        this.finalFeedbackContent = finalFeedbackContent;
        this.additionalFeedbackTitle = additionalFeedbackTitle;
        this.additionalFeedbackContent = additionalFeedbackContent;
        this.feedbackSummary = feedbackSummary;
        this.comment = comment;
        this.status = status.getLabel();
        this.reviewerName = reviewerName;
        this.reviewerBiography = reviewerBiography;
        this.applicantId = applicantId;
        this.requestDetails = requestDetails;
        this.position = position;
        this.resumeUrl = resumeUrl;
        this.resumeFile = resumeFile;
        this.name = name;
        this.email = email;
    }
}
