package kr.mainstream.seolyu.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -903745447L;

    public static final QMember member = new QMember("member1");

    public final kr.mainstream.seolyu.common.model.QBaseEntityCreateUpdateAggregate _super = new kr.mainstream.seolyu.common.model.QBaseEntityCreateUpdateAggregate(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> createdBy = _super.createdBy;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<MemberJoinStatus> joinStatus = createEnum("joinStatus", MemberJoinStatus.class);

    public final StringPath name = createString("name");

    public final StringPath occupation = createString("occupation");

    public final StringPath password = createString("password");

    public final EnumPath<JobPosition> position = createEnum("position", JobPosition.class);

    public final EnumPath<MemberRole> role = createEnum("role", MemberRole.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final NumberPath<Long> updatedBy = _super.updatedBy;

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

