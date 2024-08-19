package kr.mainstream.seolyu.domain.resumeReview.admin;

import kr.mainstream.seolyu.domain.reviewer.ReviewerService;
import kr.mainstream.seolyu.domain.resumeReview.ResumeReview;
import kr.mainstream.seolyu.domain.resumeReview.ResumeReviewQueryRepository;
import kr.mainstream.seolyu.domain.resumeReview.ResumeReviewRepository;
import kr.mainstream.seolyu.domain.resumeReview.status.ResumeReviewStatusFactory;
import kr.mainstream.seolyu.domain.resumeReview.dto.ResumeReviewGetDetailResDto;
import kr.mainstream.seolyu.domain.resumeReview.dto.ResumeReviewPutReqDto;
import kr.mainstream.seolyu.domain.resumeReview.exception.ResumeReviewNotFoundException;
import kr.mainstream.seolyu.domain.resumeReview.status.ResumeReviewStatus;
import kr.mainstream.seolyu.domain.resumeReview.status.ResumeReviewStatusHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResumeReviewAdminService {
    private final ResumeReviewQueryRepository resumeReviewQueryRepository;
    private final ResumeReviewRepository resumeReviewRepository;
    private final ReviewerService reviewerService;
    private final ResumeReviewStatusFactory resumeReviewStatusFactory;

    public ResumeReview getResumeReview(String id) {
        return resumeReviewRepository.findById(id)
                .orElseThrow(ResumeReviewNotFoundException::new);
    }

    public ResumeReviewGetDetailResDto get(String id) {
        return resumeReviewQueryRepository.findById(id)
                .orElseThrow(ResumeReviewNotFoundException::new);
    }

    @Transactional
    public void put(String id, ResumeReviewPutReqDto dto, Long reviewerId) {
        reviewerService.checkExists(reviewerId);

        ResumeReview resumeReview = this.getResumeReview(id);
        resumeReview.put(dto, reviewerId);
    }

    @Transactional
    public void updateStatusWithHandle(String id, ResumeReviewStatus status) {
        ResumeReview resumeReview = this.getResumeReview(id);
        resumeReview.updateStatus(status);

        ResumeReviewStatusHandler statusHandler = resumeReviewStatusFactory.get(status);
        statusHandler.handle(id);
    }
}
