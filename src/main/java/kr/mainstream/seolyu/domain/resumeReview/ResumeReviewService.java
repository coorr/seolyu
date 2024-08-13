package kr.mainstream.seolyu.domain.resumeReview;

import jakarta.mail.internet.MimeMessage;
import kr.mainstream.seolyu.domain.applicant.Applicant;
import kr.mainstream.seolyu.domain.applicant.ApplicantService;
import kr.mainstream.seolyu.domain.resumeReview.dto.ResumeReviewCreateReqDto;
import kr.mainstream.seolyu.domain.resumeReview.exception.ResumeReviewNotFoundException;
import kr.mainstream.seolyu.infrastructure.email.EmailMessageBuilder;
import kr.mainstream.seolyu.infrastructure.email.EmailMessageSender;
import kr.mainstream.seolyu.infrastructure.email.template.RequestCompleteEmailTemplate;
import kr.mainstream.seolyu.infrastructure.file.FileMetadata;
import kr.mainstream.seolyu.infrastructure.file.LocalFileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResumeReviewService {
    private final LocalFileStorageService localFileStorageService;
    private final ApplicantService applicantService;
    private final ResumeReviewRepository resumeReviewRepository;
    private final ResumeReviewQueryRepository resumeReviewQueryRepository;
    private final EmailMessageBuilder emailMessageBuilder;
    private final EmailMessageSender emailMessageSender;

    public ResumeReview getResumeReview(String id) {
        return resumeReviewRepository.findById(id)
                .orElseThrow(ResumeReviewNotFoundException::new);
    }

    @Transactional
    public void post(ResumeReviewCreateReqDto dto) {
        // ToDo : 이메일만 확인해서 아직 서류 검토(승인여부 확인)를 하고 있는지 확인

        FileMetadata metadata = localFileStorageService.upload(dto.getFile());

        Applicant applicant = new Applicant(dto.getName(), dto.getEmail());
        applicantService.save(applicant);

        ResumeReview resumeReview = dto.toEntity(applicant.getId(), metadata.getFilePath());
        resumeReviewRepository.save(resumeReview);

        MimeMessage message = emailMessageBuilder.build(dto.getEmail(), RequestCompleteEmailTemplate.create(dto.getName()));
        emailMessageSender.send(message);
    }
}
