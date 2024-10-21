package kr.mainstream.seolyu.infrastructure.message.event.template;

import kr.mainstream.seolyu.domain.applicant.definition.JobPosition;
import kr.mainstream.seolyu.domain.applicant.dto.ApplicantCreateReqDto;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class EventApplicantCreateMessageTemplate {
    private String name;
    private String email;
    private JobPosition position;
    private String resumeUrl;
    private String requestDetails;
    private Long eventId;
    private byte[] file;

    public EventApplicantCreateMessageTemplate(ApplicantCreateReqDto dto) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.position = dto.getPosition();
        this.resumeUrl = dto.getResumeUrl();
        this.requestDetails = dto.getRequestDetails();
        this.eventId = dto.getEventId();
        try {
            this.file = dto.getFile().getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

