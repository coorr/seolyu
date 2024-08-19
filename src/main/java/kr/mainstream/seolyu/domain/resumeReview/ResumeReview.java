package kr.mainstream.seolyu.domain.resumeReview;

import jakarta.persistence.*;
import kr.mainstream.seolyu.common.model.BaseEntityCreateUpdateAggregate;
import kr.mainstream.seolyu.domain.applicant.Applicant;
import kr.mainstream.seolyu.domain.resumeReview.status.ResumeReviewStatus;
import kr.mainstream.seolyu.domain.resumeReview.dto.ResumeReviewPutReqDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static kr.mainstream.seolyu.domain.resumeReview.status.ResumeReviewStatus.*;

@Entity(name = "resume_review")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResumeReview extends BaseEntityCreateUpdateAggregate {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "a_id")
    private Long applicantId;

    @Column(name = "r_id")
    private Long reviewerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private ResumeReviewStatus status;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Column(name = "summary")
    private String summary;

    @Column(name = "comment")
    private String comment;

    @Column(name = "initial_title")
    private String initialTitle;

    @Column(name = "initial_content")
    private String initialContent;

    @Column(name = "midterm_title")
    private String midtermTitle;

    @Column(name = "midterm_content")
    private String midtermContent;

    @Column(name = "final_title")
    private String finalTitle;

    @Column(name = "final_content")
    private String finalContent;

    @Column(name = "additional_title")
    private String additionalTitle;

    @Column(name = "additional_content")
    private String additionalContent;

    @OneToOne
    @JoinColumn(name = "a_id", insertable = false, updatable = false)
    private Applicant applicant;

    public ResumeReview(Long applicantId) {
        this.applicantId = applicantId;
        this.status = READY;
    }

    public void put(ResumeReviewPutReqDto dto, Long reviewerId) {
        this.summary = dto.getFeedbackSummary();
        this.initialTitle = dto.getInitialFeedbackTitle();
        this.initialContent = dto.getInitialFeedbackContent();
        this.midtermTitle = dto.getMidtermFeedbackTitle();
        this.midtermContent = dto.getMidtermFeedbackContent();
        this.finalTitle = dto.getFinalFeedbackTitle();
        this.finalContent = dto.getFinalFeedbackContent();
        this.additionalTitle = dto.getAdditionalFeedbackTitle();
        this.additionalContent = dto.getAdditionalFeedbackContent();
        this.comment = dto.getComment();
        this.reviewerId = reviewerId;
    }

    public void updateStatus(ResumeReviewStatus status) {
        this.status = status;
        if (status == COMPLETED) {
            this.completedAt = LocalDateTime.now();
        }
    }
}
