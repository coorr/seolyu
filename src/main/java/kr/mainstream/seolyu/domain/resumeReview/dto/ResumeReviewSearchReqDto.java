package kr.mainstream.seolyu.domain.resumeReview.dto;

import kr.mainstream.seolyu.domain.resumeReview.definition.ExpiredSearchType;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class ResumeReviewSearchReqDto {
    private String name;
    private String email;
    private ExpiredSearchType expiredSearchType;
    private String startedDate;
    private String endedDate;
    private Pageable pageable;

    public ResumeReviewSearchReqDto(ResumeReviewGetReqDto dto) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.expiredSearchType = ExpiredSearchType.valueOf(dto.getIsExpired());
        this.startedDate = dto.getStartedDate();
        this.endedDate = dto.getEndedDate();
        this.pageable = dto.generatePage();
    }
}
