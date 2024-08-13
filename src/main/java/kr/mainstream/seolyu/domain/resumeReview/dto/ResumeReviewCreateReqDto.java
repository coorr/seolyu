package kr.mainstream.seolyu.domain.resumeReview.dto;

import kr.mainstream.seolyu.domain.resumeReview.JobPosition;
import kr.mainstream.seolyu.domain.resumeReview.ResumeReview;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class ResumeReviewCreateReqDto {
    private String name;

    private String email;

    private JobPosition position;

    private String httpUrl;
    private String requestDetails;
    private MultipartFile file;

    public ResumeReviewCreateReqDto(ResumeReviewPostReqDto dto, MultipartFile file) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.position = JobPosition.valueOf(dto.getPosition());
        this.httpUrl = dto.getHttpUrl();
        this.requestDetails = dto.getHttpUrl();
        this.file = file;
    }

    public ResumeReview toEntity(Long applicantId, String fileUrl) {
        return new ResumeReview(applicantId, position, httpUrl, fileUrl, requestDetails);
    }
}
