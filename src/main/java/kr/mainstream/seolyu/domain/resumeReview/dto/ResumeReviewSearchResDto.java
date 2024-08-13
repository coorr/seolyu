package kr.mainstream.seolyu.domain.resumeReview.dto;

import kr.mainstream.seolyu.domain.resumeReview.JobPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ResumeReviewSearchResDto {
    private String id;
    private Long applicantId;
    private Long resumeFeedbackId;
    private JobPosition position;
    private String httpUrl;
    private String fileUrl;
    private boolean isExpired;
    private LocalDateTime expiredAt;
    private String name;
    private String email;
}
