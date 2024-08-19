package kr.mainstream.seolyu.domain.applicant.dto;

import kr.mainstream.seolyu.domain.applicant.definition.JobPosition;
import kr.mainstream.seolyu.domain.resumeReview.status.ResumeReviewStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApplicantSearchResDto {
    private Long id;
    private String name;
    private String email;
    private JobPosition position;
    private String resumeReviewId;
    private String status;
    private LocalDateTime completedAt;

    public ApplicantSearchResDto(Long id, String name, String email, JobPosition position, String resumeReviewId,
                                 ResumeReviewStatus status, LocalDateTime completedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.position = position;
        this.resumeReviewId = resumeReviewId;
        this.status = status.getLabel();
        this.completedAt = completedAt;
    }
}
