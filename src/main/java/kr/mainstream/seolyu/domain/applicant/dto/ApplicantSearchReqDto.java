package kr.mainstream.seolyu.domain.applicant.dto;

import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class ApplicantSearchReqDto {
    private String name;
    private String email;
    private String status;
    private String startedDate;
    private String endedDate;
    private Pageable pageable;

    public ApplicantSearchReqDto(ApplicantGetReqDto dto) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.status = dto.getStatus();
        this.startedDate = dto.getStartedDate();
        this.endedDate = dto.getEndedDate();
        this.pageable = dto.generatePage();
    }
}
