package kr.mainstream.seolyu.common.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseEntityCreateUpdateAggregate is a Querydsl query type for BaseEntityCreateUpdateAggregate
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseEntityCreateUpdateAggregate extends EntityPathBase<BaseEntityCreateUpdateAggregate> {

    private static final long serialVersionUID = 106667123L;

    public static final QBaseEntityCreateUpdateAggregate baseEntityCreateUpdateAggregate = new QBaseEntityCreateUpdateAggregate("baseEntityCreateUpdateAggregate");

    public final QBaseEntityCreateAggregate _super = new QBaseEntityCreateAggregate(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> createdBy = _super.createdBy;

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> updatedBy = createNumber("updatedBy", Long.class);

    public QBaseEntityCreateUpdateAggregate(String variable) {
        super(BaseEntityCreateUpdateAggregate.class, forVariable(variable));
    }

    public QBaseEntityCreateUpdateAggregate(Path<? extends BaseEntityCreateUpdateAggregate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseEntityCreateUpdateAggregate(PathMetadata metadata) {
        super(BaseEntityCreateUpdateAggregate.class, metadata);
    }

}

