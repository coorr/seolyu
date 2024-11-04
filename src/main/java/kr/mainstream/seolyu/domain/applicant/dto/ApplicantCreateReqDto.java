package kr.mainstream.seolyu.domain.applicant.dto;

import kr.mainstream.seolyu.domain.applicant.definition.JobPosition;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
public class ApplicantCreateReqDto {
    private String name;
    private String email;
    private JobPosition position;
    private String resumeUrl;
    private String requestDetails;
    private Long eventId;
    private MultipartFile file;
    private LocalDateTime currentDateTime;

    public ApplicantCreateReqDto(ApplicantPostReqDto dto, MultipartFile file) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.position = JobPosition.valueOf(dto.getPosition());
        this.resumeUrl = dto.getHttpUrl();
        this.requestDetails = dto.getRequestDetails();
        this.eventId = Long.valueOf(dto.getEventId());
        this.file = file;
        this.currentDateTime = LocalDateTime.now();
    }
}
