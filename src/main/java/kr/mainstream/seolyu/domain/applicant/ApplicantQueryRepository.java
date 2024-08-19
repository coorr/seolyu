package kr.mainstream.seolyu.domain.applicant;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.mainstream.seolyu.common.utils.QueryWhereConditionUtils;
import kr.mainstream.seolyu.domain.applicant.dto.ApplicantSearchReqDto;
import kr.mainstream.seolyu.domain.applicant.dto.ApplicantSearchResDto;
import kr.mainstream.seolyu.domain.resumeReview.status.ResumeReviewStatus;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

import static kr.mainstream.seolyu.domain.applicant.QApplicant.applicant;
import static kr.mainstream.seolyu.domain.resumeReview.QResumeReview.resumeReview;

@Repository
@RequiredArgsConstructor
public class ApplicantQueryRepository {
    private final JPAQueryFactory factory;

    public Page<ApplicantSearchResDto> findAllSearchConditions(ApplicantSearchReqDto dto) {
        Long count = factory.select(applicant.count())
                .from(applicant)
                .innerJoin(resumeReview).on(resumeReview.applicantId.eq(applicant.id))
                .where(getSearchWhereClauses(dto))
                .fetchFirst();

        if (count == 0) {
            return new PageImpl<>(Collections.emptyList(), dto.getPageable(), count);
        }

        List<ApplicantSearchResDto> list = factory
                .select(Projections.constructor(ApplicantSearchResDto.class,
                        applicant.id,
                        applicant.name,
                        applicant.email,
                        applicant.position,
                        resumeReview.id,
                        resumeReview.status,
                        resumeReview.completedAt
                ))
                .from(applicant)
                .innerJoin(resumeReview).on(resumeReview.applicantId.eq(applicant.id))
                .where(getSearchWhereClauses(dto))
                .orderBy(applicant.id.desc())
                .offset(dto.getPageable().getOffset())
                .limit(dto.getPageable().getPageSize())
                .fetch();

        return new PageImpl<>(list, dto.getPageable(), count);
    }

    private BooleanExpression[] getSearchWhereClauses(ApplicantSearchReqDto dto) {
        return new BooleanExpression[]{
                containsName(dto.getName()),
                containsEmail(dto.getEmail()),
                eqResumeReviewStatus(dto.getStatus()),
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

    private BooleanExpression eqResumeReviewStatus(String status) {
        if (StringUtils.isBlank(status)) {
            return null;
        }
        return resumeReview.status.eq(ResumeReviewStatus.valueOf(status));
    }
}
