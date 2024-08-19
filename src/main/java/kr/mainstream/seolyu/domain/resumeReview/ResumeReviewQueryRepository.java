package kr.mainstream.seolyu.domain.resumeReview;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.mainstream.seolyu.domain.resumeReview.dto.ResumeReviewGetDetailResDto;
import kr.mainstream.seolyu.domain.resumeReview.dto.ResumeReviewGetResDto;
import kr.mainstream.seolyu.domain.resumeReview.status.ResumeReviewStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static kr.mainstream.seolyu.domain.applicant.QApplicant.applicant;
import static kr.mainstream.seolyu.domain.resumeReview.QResumeReview.resumeReview;
import static kr.mainstream.seolyu.domain.reviewer.QReviewer.reviewer;

@Repository
@RequiredArgsConstructor
public class ResumeReviewQueryRepository {
    private final JPAQueryFactory factory;

    public boolean isExistsByEmailAndStatusReady(String email) {
        Integer fetchOne = factory.selectOne()
                .from(resumeReview)
                .innerJoin(applicant).on(applicant.id.eq(resumeReview.applicantId))
                .where(
                        applicant.email.eq(email),
                        resumeReview.status.eq(ResumeReviewStatus.READY)
                )
                .fetchFirst();
        return fetchOne != null;
    }

    public ResumeReviewGetResDto findByIdAndStatusCompleted(String id) {
        return factory.select(Projections.constructor(ResumeReviewGetResDto.class,
                        resumeReview.id,
                        resumeReview.initialTitle,
                        resumeReview.initialContent,
                        resumeReview.midtermTitle,
                        resumeReview.midtermContent,
                        resumeReview.finalTitle,
                        resumeReview.finalContent,
                        resumeReview.additionalTitle,
                        resumeReview.additionalContent,
                        resumeReview.summary,
                        resumeReview.comment,
                        reviewer.biography,
                        applicant.name
                        ))
                .from(resumeReview)
                .innerJoin(applicant).on(applicant.id.eq(resumeReview.applicantId))
                .innerJoin(reviewer).on(reviewer.id.eq(resumeReview.reviewerId))
                .where(
                        resumeReview.id.eq(id),
                        resumeReview.status.eq(ResumeReviewStatus.COMPLETED)
                )
                .fetchOne();
    }

    public Optional<ResumeReviewGetDetailResDto> findById(String id) {
        return Optional.ofNullable(factory
                .select(Projections.constructor(ResumeReviewGetDetailResDto.class,
                        resumeReview.id,
                        resumeReview.initialTitle,
                        resumeReview.initialContent,
                        resumeReview.midtermTitle,
                        resumeReview.midtermContent,
                        resumeReview.finalTitle,
                        resumeReview.finalContent,
                        resumeReview.additionalTitle,
                        resumeReview.additionalContent,
                        resumeReview.summary,
                        resumeReview.comment,
                        resumeReview.status,
                        reviewer.name,
                        reviewer.biography,
                        applicant.id,
                        applicant.requestDetails,
                        applicant.position,
                        applicant.resumeUrl,
                        applicant.resumeFile,
                        applicant.name,
                        applicant.email
                ))
                .from(resumeReview)
                .innerJoin(applicant).on(applicant.id.eq(resumeReview.applicantId))
                .leftJoin(reviewer).on(reviewer.id.eq(resumeReview.reviewerId))
                .where(resumeReview.id.eq(id))
                .fetchOne());
    }
}
