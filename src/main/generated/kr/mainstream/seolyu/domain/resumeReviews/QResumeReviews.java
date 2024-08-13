package kr.mainstream.seolyu.domain.resumeReview;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QResumeReviews is a Querydsl query type for ResumeReviews
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResumeReviews extends EntityPathBase<ResumeReviews> {

    private static final long serialVersionUID = -542691111L;

    public static final QResumeReviews resumeReviews = new QResumeReviews("resumeReviews");

    public final kr.mainstream.seolyu.common.model.QBaseEntityCreateUpdateAggregate _super = new kr.mainstream.seolyu.common.model.QBaseEntityCreateUpdateAggregate(this);

    public final NumberPath<Long> applicantId = createNumber("applicantId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> createdBy = _super.createdBy;

    public final DateTimePath<java.time.LocalDateTime> expiredAt = createDateTime("expiredAt", java.time.LocalDateTime.class);

    public final StringPath fileUrl = createString("fileUrl");

    public final StringPath httpUrl = createString("httpUrl");

    public final StringPath id = createString("id");

    public final BooleanPath isExpired = createBoolean("isExpired");

    public final EnumPath<JobPosition> position = createEnum("position", JobPosition.class);

    public final StringPath requestDetails = createString("requestDetails");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final NumberPath<Long> updatedBy = _super.updatedBy;

    public QResumeReviews(String variable) {
        super(ResumeReviews.class, forVariable(variable));
    }

    public QResumeReviews(Path<? extends ResumeReviews> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResumeReviews(PathMetadata metadata) {
        super(ResumeReviews.class, metadata);
    }

}

