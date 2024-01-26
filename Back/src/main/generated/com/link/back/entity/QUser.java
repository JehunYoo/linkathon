package com.link.back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1708019882L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final DatePath<java.time.LocalDate> birth = createDate("birth", java.time.LocalDate.class);

    public final NumberPath<Integer> career = createNumber("career", Integer.class);

    public final StringPath deployUrl = createString("deployUrl");

    public final StringPath email = createString("email");

    public final EnumPath<Field> field = createEnum("field", Field.class);

    public final BooleanPath gender = createBoolean("gender");

    public final StringPath introduce = createString("introduce");

    public final BooleanPath joinState = createBoolean("joinState");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final NumberPath<Integer> rating = createNumber("rating", Integer.class);

    public final StringPath referenceUrl = createString("referenceUrl");

    public final BooleanPath registered = createBoolean("registered");

    public final DatePath<java.time.LocalDate> registeredDate = createDate("registeredDate", java.time.LocalDate.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final QUserImage userImage;

    public final ListPath<UserSkill, QUserSkill> userSkills = this.<UserSkill, QUserSkill>createList("userSkills", UserSkill.class, QUserSkill.class, PathInits.DIRECT2);

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userImage = inits.isInitialized("userImage") ? new QUserImage(forProperty("userImage"), inits.get("userImage")) : null;
    }

}

