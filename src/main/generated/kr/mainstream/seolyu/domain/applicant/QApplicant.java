package kr.mainstream.seolyu.domain.applicant;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QApplicant is a Querydsl query type for Applicant
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QApplicant extends EntityPathBase<Applicant> {

    private static final long serialVersionUID = -1341934975L;

    public static final QApplicant applicant = new QApplicant("applicant");

    public final kr.mainstream.seolyu.common.model.QBaseEntityCreateUpdateAggregate _super = new kr.mainstream.seolyu.common.model.QBaseEntityCreateUpdateAggregate(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> createdBy = _super.createdBy;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final NumberPath<Long> updatedBy = _super.updatedBy;

    public QApplicant(String variable) {
        super(Applicant.class, forVariable(variable));
    }

    public QApplicant(Path<? extends Applicant> path) {
        super(path.getType(), path.getMetadata());
    }

    public QApplicant(PathMetadata metadata) {
        super(Applicant.class, metadata);
    }

}

