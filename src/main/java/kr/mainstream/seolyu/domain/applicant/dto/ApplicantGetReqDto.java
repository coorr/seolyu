package kr.mainstream.seolyu.domain.applicant.dto;

import kr.mainstream.seolyu.common.page.PageReqDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicantGetReqDto extends PageReqDto {
    private String name;
    private String email;
    private String status;
    private String startedDate;
    private String endedDate;
}
