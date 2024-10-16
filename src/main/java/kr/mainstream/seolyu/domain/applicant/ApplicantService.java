package kr.mainstream.seolyu.domain.applicant;

import kr.mainstream.seolyu.domain.applicant.dto.ApplicantCreateReqDto;
import kr.mainstream.seolyu.domain.event.EventService;
import kr.mainstream.seolyu.domain.event.applicationIssue.EventApplicationIssue;
import kr.mainstream.seolyu.domain.event.applicationIssue.EventApplicationIssueService;
import kr.mainstream.seolyu.domain.resumeReview.ResumeReviewService;
import kr.mainstream.seolyu.infrastructure.file.FileMetadata;
import kr.mainstream.seolyu.infrastructure.file.FileStorageService;
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
    private final EventApplicationIssueService eventApplicationIssueService;

    @Transactional
    public Applicant save(ApplicantCreateReqDto dto, LocalDateTime currentDateTime) {
        eventService.apply(dto.getEventId(), dto.getEmail(), currentDateTime);

        FileMetadata metadata = fileStorageService.upload(dto.getFile());

        Applicant applicant = dto.toEntity(metadata.getFilePath());
        applicantRepository.save(applicant);
        resumeReviewService.initialize(applicant.getId());
        eventApplicationIssueService.save(new EventApplicationIssue(dto.getEventId(), applicant.getId()));
        return applicant;
    }
}
