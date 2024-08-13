package kr.mainstream.seolyu.domain.resumeReview;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.mainstream.seolyu.common.utils.QueryWhereConditionUtils;
import kr.mainstream.seolyu.domain.resumeReview.definition.ExpiredSearchType;
import kr.mainstream.seolyu.domain.resumeReview.dto.ResumeReviewGetSummaryResDto;
import kr.mainstream.seolyu.domain.resumeReview.dto.ResumeReviewSearchReqDto;
import kr.mainstream.seolyu.domain.resumeReview.dto.ResumeReviewSearchResDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static kr.mainstream.seolyu.domain.applicant.QApplicant.applicant;
import static kr.mainstream.seolyu.domain.resumeFeedback.QResumeFeedback.resumeFeedback;
import static kr.mainstream.seolyu.domain.resumeReview.QResumeReview.resumeReview;
import static kr.mainstream.seolyu.domain.resumeReview.definition.ExpiredSearchType.N;
import static kr.mainstream.seolyu.domain.resumeReview.definition.ExpiredSearchType.Y;

@Repository
@RequiredArgsConstructor
public class ResumeReviewQueryRepository {
    private final JPAQueryFactory factory;

    public Optional<ResumeReviewGetSummaryResDto> findById(String id) {
        return Optional.ofNullable(factory
                .select(Projections.constructor(ResumeReviewGetSummaryResDto.class,
                        resumeReview.id,
                        resumeReview.requestDetails,
                        resumeReview.position,
                        resumeReview.httpUrl,
                        resumeReview.fileUrl,
                        applicant.name,
                        applicant.email,
                        resumeFeedback.id,
                        resumeFeedback.initialTitle,
                        resumeFeedback.initialContent,
                        resumeFeedback.midtermTitle,
                        resumeFeedback.midtermContent,
                        resumeFeedback.finalTitle,
                        resumeFeedback.finalContent,
                        resumeFeedback.additionalTitle,
                        resumeFeedback.additionalContent,
                        resumeFeedback.summary,
                        resumeFeedback.comment
                ))
                .from(resumeReview)
                .innerJoin(applicant).on(applicant.id.eq(resumeReview.applicantId))
                .leftJoin(resumeFeedback).on(resumeFeedback.resumeReviewId.eq(resumeReview.id))
                .where(resumeReview.id.eq(id))
                .fetchOne());
    }

    public Page<ResumeReviewSearchResDto> findAllSearchConditions(ResumeReviewSearchReqDto dto) {
        Long count = factory.select(resumeReview.count())
                .from(resumeReview)
                .innerJoin(applicant).on(applicant.id.eq(resumeReview.applicantId))
                .leftJoin(resumeFeedback).on(resumeFeedback.resumeReviewId.eq(resumeReview.id))
                .where(getSearchWhereClauses(dto))
                .fetchFirst();

        if (count == 0) {
            return new PageImpl<>(Collections.emptyList(), dto.getPageable(), count);
        }

        List<ResumeReviewSearchResDto> list = factory
                .select(Projections.constructor(ResumeReviewSearchResDto.class,
                        resumeReview.id,
                        applicant.id,
                        resumeFeedback.id,
                        resumeReview.position,
                        resumeReview.httpUrl,
                        resumeReview.fileUrl,
                        resumeReview.isExpired,
                        resumeReview.expiredAt,
                        applicant.name,
                        applicant.email
                ))
                .from(resumeReview)
                .innerJoin(applicant).on(applicant.id.eq(resumeReview.applicantId))
                .leftJoin(resumeFeedback).on(resumeFeedback.resumeReviewId.eq(resumeReview.id))
                .where(getSearchWhereClauses(dto))
                .orderBy(applicant.id.desc())
                .offset(dto.getPageable().getOffset())
                .limit(dto.getPageable().getPageSize())
                .fetch();

        return new PageImpl<>(list, dto.getPageable(), count);
    }

    private BooleanExpression[] getSearchWhereClauses(ResumeReviewSearchReqDto dto) {
        return new BooleanExpression[]{
                containsName(dto.getName()),
                containsEmail(dto.getEmail()),
                eqIsExpired(dto.getExpiredSearchType()),
                QueryWhereConditionUtils.compareDateTimes(resumeReview.createdAt, dto.getStartedDate(), dto.getEndedDate())
        };
    }

    private BooleanExpression containsName(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        return applicant.name.contains(name);
    }

    private BooleanExpression containsEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return null;
        }
        return applicant.email.contains(email);
    }

    private BooleanExpression eqIsExpired(ExpiredSearchType expiredSearchType) {
        if (expiredSearchType == Y) {
            return resumeReview.isExpired.eq(true);
        }
        if (expiredSearchType == N) {
            return resumeReview.isExpired.eq(false);
        }
        return null;
    }


}
