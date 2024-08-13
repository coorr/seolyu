package kr.mainstream.seolyu.domain.resumeReview;

import jakarta.persistence.*;
import kr.mainstream.seolyu.common.model.BaseEntityCreateUpdateAggregate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "position", nullable = false, length = 20)
    private JobPosition position;

    @Column(name = "http_url")
    private String httpUrl;

    @Column(name = "file_url", nullable = false)
    private String fileUrl;

    @Column(name = "request_details")
    private String requestDetails;

    @Column(name = "is_expired")
    private boolean isExpired = false;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    public ResumeReview(Long applicantId, JobPosition position, String httpUrl, String fileUrl, String requestDetails) {
        this.applicantId = applicantId;
        this.position = position;
        this.httpUrl = httpUrl;
        this.fileUrl = fileUrl;
        this.requestDetails = requestDetails;
    }
}
