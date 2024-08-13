package kr.mainstream.seolyu.domain.resumeFeedback;

import jakarta.persistence.*;
import kr.mainstream.seolyu.common.model.BaseEntityCreateUpdateAggregate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "resume_feedback")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResumeFeedback extends BaseEntityCreateUpdateAggregate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "rr_id")
    private String resumeReviewId;

    @Column(name = "m_id")
    private Long memberId;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(name = "initial_title", nullable = false)
    private String initialTitle;

    @Column(name = "initial_content")
    private String initialContent;

    @Column(name = "midterm_title", nullable = false)
    private String midtermTitle;

    @Column(name = "midterm_content")
    private String midtermContent;

    @Column(name = "final_title", nullable = false)
    private String finalTitle;

    @Column(name = "final_content")
    private String finalContent;

    @Column(name = "additional_title")
    private String additionalTitle;

    @Column(name = "additional_content", nullable = false)
    private String additionalContent;

    @Column(name = "comment", nullable = false)
    private String comment;

    public ResumeFeedback(String summary, String initialTitle, String initialContent, String midtermTitle,
                          String midtermContent, String finalTitle, String finalContent, String additionalTitle,
                          String additionalContent, String comment, String resumeReviewId, Long memberId) {
        this.summary = summary;
        this.initialTitle = initialTitle;
        this.initialContent = initialContent;
        this.midtermTitle = midtermTitle;
        this.midtermContent = midtermContent;
        this.finalTitle = finalTitle;
        this.finalContent = finalContent;
        this.additionalTitle = additionalTitle;
        this.additionalContent = additionalContent;
        this.comment = comment;
        this.resumeReviewId = resumeReviewId;
        this.memberId = memberId;
    }
}
