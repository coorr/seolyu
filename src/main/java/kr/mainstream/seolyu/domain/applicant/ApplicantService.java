package kr.mainstream.seolyu.domain.applicant;

import kr.mainstream.seolyu.domain.applicant.dto.ApplicantCreateReqDto;
import kr.mainstream.seolyu.domain.event.EventService;
import kr.mainstream.seolyu.domain.event.applicationIssue.EventApplicantHistory;
import kr.mainstream.seolyu.domain.event.applicationIssue.EventApplicantHistoryService;
import kr.mainstream.seolyu.domain.resumeReview.ResumeReviewService;
import kr.mainstream.seolyu.infrastructure.file.FileMetadata;
import kr.mainstream.seolyu.infrastructure.file.FileStorageService;
import kr.mainstream.seolyu.infrastructure.message.Message;
import kr.mainstream.seolyu.infrastructure.message.MessageSender;
import kr.mainstream.seolyu.infrastructure.message.event.TemplateType;
import kr.mainstream.seolyu.infrastructure.message.event.builder.EventMessageBuilderFactory;
import kr.mainstream.seolyu.infrastructure.message.event.template.EventApplicantCreateMessageTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ApplicantService {
    private final ApplicantRepository applicantRepository;
    private final FileStorageService fileStorageService;
    private final ResumeReviewService resumeReviewService;
    private final EventService eventService;
    private final EventApplicantHistoryService eventApplicantHistoryService;
    private final MessageSender eventMessageSender;

    @Transactional
    public Applicant save(ApplicantCreateReqDto dto, LocalDateTime currentDateTime) {
        eventService.apply(dto.getEventId(), dto.getEmail(), currentDateTime);

        FileMetadata metadata = fileStorageService.upload(dto.getFile());

        Applicant applicant = dto.toEntity(metadata.getFilePath());
        applicantRepository.save(applicant);
        resumeReviewService.initialize(applicant.getId());
        eventApplicantHistoryService.save(new EventApplicantHistory(dto.getEventId(), applicant.getId()));
        return applicant;
    }

    @Transactional
    public void saveMq(ApplicantCreateReqDto dto, LocalDateTime currentDateTime) {
        eventService.applyMq(dto.getEventId(), dto.getEmail(), currentDateTime);

        Message message = new EventMessageBuilderFactory()
                .createBuilder(TemplateType.EVENT_APPLICANT_CREATED)
                .message(new EventApplicantCreateMessageTemplate(dto))
                .build();
        eventMessageSender.send(message);
    }
}
