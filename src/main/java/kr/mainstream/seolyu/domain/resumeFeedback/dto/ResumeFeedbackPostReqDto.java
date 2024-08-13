package kr.mainstream.seolyu.domain.resumeFeedback.dto;

import jakarta.validation.constraints.NotBlank;
import kr.mainstream.seolyu.domain.resumeFeedback.ResumeFeedback;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumeFeedbackPostReqDto {
    @NotBlank(message = "이력서 검토 ID는 필수입니다.")
    private String resumeReviewId;
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

    public ResumeFeedback toEntity(Long memberId, String resumeReviewId) {
        return new ResumeFeedback(
                this.feedbackSummary,
                this.initialFeedbackTitle,
                this.initialFeedbackContent,
                this.midtermFeedbackTitle,
                this.midtermFeedbackContent,
                this.finalFeedbackTitle,
                this.finalFeedbackContent,
                this.additionalFeedbackTitle,
                this.additionalFeedbackContent,
                this.comment,
                resumeReviewId,
                memberId
        );
    }
}
