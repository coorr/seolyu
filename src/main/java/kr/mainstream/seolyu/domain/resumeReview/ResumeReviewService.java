package kr.mainstream.seolyu.domain.resumeReview;

import kr.mainstream.seolyu.domain.applicant.ApplicantReadService;
import kr.mainstream.seolyu.domain.resumeReview.dto.ResumeReviewGetResDto;
import kr.mainstream.seolyu.domain.resumeReview.exception.PendingResumeReviewException;
import kr.mainstream.seolyu.domain.resumeReview.exception.ResumeReviewNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResumeReviewService {
    private final ApplicantReadService applicantReadService;
    private final ResumeReviewRepository resumeReviewRepository;
    private final ResumeReviewQueryRepository resumeReviewQueryRepository;

    public ResumeReview getResumeReview(String id) {
        return resumeReviewRepository.findById(id)
                .orElseThrow(ResumeReviewNotFoundException::new);
    }

    public ResumeReviewGetResDto getCompletedResumeReview(String id) {
        return resumeReviewQueryRepository.findByIdAndStatusCompleted(id);
    }

    @Transactional
    public ResumeReview initialize(Long applicantId) {
        applicantReadService.checkExists(applicantId);

        ResumeReview resumeReview = new ResumeReview(applicantId);
        resumeReviewRepository.save(resumeReview);
        return resumeReview;
    }

    public void checkPending(String email) {
        boolean isExists = resumeReviewQueryRepository.isExistsByEmailAndStatusReady(email);
        if (isExists) {
            throw new PendingResumeReviewException();
        }
    }
}
