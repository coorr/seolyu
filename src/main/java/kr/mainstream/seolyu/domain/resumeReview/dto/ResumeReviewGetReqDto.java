package kr.mainstream.seolyu.domain.resumeReview.dto;

import kr.mainstream.seolyu.common.page.PageReqDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumeReviewGetReqDto extends PageReqDto {
    private String name;
    private String email;
    private String isExpired;
    private String startedDate;
    private String endedDate;
}
