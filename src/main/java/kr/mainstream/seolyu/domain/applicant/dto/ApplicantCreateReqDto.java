package kr.mainstream.seolyu.domain.applicant.dto;

import kr.mainstream.seolyu.domain.applicant.Applicant;
import kr.mainstream.seolyu.domain.applicant.definition.JobPosition;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class ApplicantCreateReqDto {
    private String name;
    private String email;
    private JobPosition position;
    private String resumeUrl;
    private String requestDetails;
    private MultipartFile file;

    public ApplicantCreateReqDto(ApplicantPostReqDto dto, MultipartFile file) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.position = JobPosition.valueOf(dto.getPosition());
        this.resumeUrl = dto.getHttpUrl();
        this.requestDetails = dto.getRequestDetails();
        this.file = file;
    }

    public Applicant toEntity(String resumeFile) {
        return new Applicant(name, email, position, resumeUrl, resumeFile, requestDetails);
    }
}
