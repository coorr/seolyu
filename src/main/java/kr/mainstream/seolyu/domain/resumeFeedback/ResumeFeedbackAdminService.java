package kr.mainstream.seolyu.domain.resumeFeedback;

import kr.mainstream.seolyu.domain.member.Member;
import kr.mainstream.seolyu.domain.member.MemberService;
import kr.mainstream.seolyu.domain.resumeFeedback.dto.ResumeFeedbackPostReqDto;
import kr.mainstream.seolyu.domain.resumeReview.ResumeReview;
import kr.mainstream.seolyu.domain.resumeReview.ResumeReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResumeFeedbackAdminService {
    private final ResumeFeedbackRepository resumeFeedbackRepository;
    private final MemberService memberService;
    private final ResumeReviewService resumeReviewService;

    @Transactional
    public void post(ResumeFeedbackPostReqDto dto, Long memberId) {
        Member member = memberService.getMember(memberId);
        ResumeReview resumeReview = resumeReviewService.getResumeReview(dto.getResumeReviewId());
        ResumeFeedback resumeFeedback = dto.toEntity(member.getId(), resumeReview.getId());
        resumeFeedbackRepository.save(resumeFeedback);
    }
}
