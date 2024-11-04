package kr.mainstream.seolyu.infrastructure.message.event.parameter;

import kr.mainstream.seolyu.domain.applicant.definition.JobPosition;
import kr.mainstream.seolyu.domain.applicant.dto.ApplicantCreateReqDto;
import kr.mainstream.seolyu.infrastructure.file.FilePayload;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventApplicantCreateTemplateParameter {
    private String name;
    private String email;
    private JobPosition position;
    private String resumeUrl;
    private String requestDetails;
    private Long eventId;
    private FilePayload file;

    public EventApplicantCreateTemplateParameter(ApplicantCreateReqDto dto) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.position = dto.getPosition();
        this.resumeUrl = dto.getResumeUrl();
        this.requestDetails = dto.getRequestDetails();
        this.eventId = dto.getEventId();
        this.file = new FilePayload(dto.getFile());
    }
}

