package kr.mainstream.seolyu.domain.resumeReview;

import kr.mainstream.seolyu.domain.resumeReview.dto.ResumeReviewGetSummaryResDto;
import kr.mainstream.seolyu.domain.resumeReview.dto.ResumeReviewSearchReqDto;
import kr.mainstream.seolyu.domain.resumeReview.dto.ResumeReviewSearchResDto;
import kr.mainstream.seolyu.domain.resumeReview.exception.ResumeReviewNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeReviewAdminService {
    private final ResumeReviewQueryRepository resumeReviewQueryRepository;

    public Page<ResumeReviewSearchResDto> getList(ResumeReviewSearchReqDto dto) {
        return resumeReviewQueryRepository.findAllSearchConditions(dto);
    }

    public ResumeReviewGetSummaryResDto getSummary(String id) {
        return resumeReviewQueryRepository.findById(id)
                .orElseThrow(ResumeReviewNotFoundException::new);
    }
}
