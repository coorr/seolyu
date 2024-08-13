package kr.mainstream.seolyu.domain.applicant.resumeReviews;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import kr.mainstream.seolyu.domain.resumeReview.ResumeReviews;
import kr.mainstream.seolyu.domain.resumeReview.JobPosition;


/**
 * QApplicantResume is a Querydsl query type for ApplicantResume
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QApplicantResume extends EntityPathBase<ResumeReviews> {

    private static final long serialVersionUID = -932571985L;

    public static final QApplicantResume applicantResume = new QApplicantResume("applicantResume");

    public final kr.mainstream.seolyu.common.model.QBaseEntityCreateUpdateAggregate _super = new kr.mainstream.seolyu.common.model.QBaseEntityCreateUpdateAggregate(this);

    public final StringPath applicantId = createString("applicantId");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> createdBy = _super.createdBy;

    public final StringPath fileUrl = createString("fileUrl");

    public final StringPath httpUrl = createString("httpUrl");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<JobPosition> position = createEnum("position", JobPosition.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final NumberPath<Long> updatedBy = _super.updatedBy;

    public QApplicantResume(String variable) {
        super(ResumeReviews.class, forVariable(variable));
    }

    public QApplicantResume(Path<? extends ResumeReviews> path) {
        super(path.getType(), path.getMetadata());
    }

    public QApplicantResume(PathMetadata metadata) {
        super(ResumeReviews.class, metadata);
    }

}

