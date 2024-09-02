package kr.mainstream.seolyu.domain.applicant;

import kr.mainstream.seolyu.domain.applicant.dto.ApplicantCreateReqDto;
import kr.mainstream.seolyu.domain.resumeReview.ResumeReviewService;
import kr.mainstream.seolyu.infrastructure.file.FileMetadata;
import kr.mainstream.seolyu.infrastructure.file.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicantService {
    private final ApplicantRepository applicantRepository;
    private final FileStorageService fileStorageService;
    private final ResumeReviewService resumeReviewService;

    @Transactional
    public Applicant save(ApplicantCreateReqDto dto) {
        resumeReviewService.checkPending(dto.getEmail());

        FileMetadata metadata = fileStorageService.upload(dto.getFile());

        Applicant applicant = dto.toEntity(metadata.getFilePath());
        applicantRepository.save(applicant);

        resumeReviewService.initialize(applicant.getId());
        return applicant;
    }
}
