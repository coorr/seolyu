package kr.mainstream.seolyu.common.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseEntityCreateAggregate is a Querydsl query type for BaseEntityCreateAggregate
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseEntityCreateAggregate extends EntityPathBase<BaseEntityCreateAggregate> {

    private static final long serialVersionUID = -339344132L;

    public static final QBaseEntityCreateAggregate baseEntityCreateAggregate = new QBaseEntityCreateAggregate("baseEntityCreateAggregate");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> createdBy = createNumber("createdBy", Long.class);

    public QBaseEntityCreateAggregate(String variable) {
        super(BaseEntityCreateAggregate.class, forVariable(variable));
    }

    public QBaseEntityCreateAggregate(Path<? extends BaseEntityCreateAggregate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseEntityCreateAggregate(PathMetadata metadata) {
        super(BaseEntityCreateAggregate.class, metadata);
    }

}

