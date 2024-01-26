package com.link.back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserSkill is a Querydsl query type for UserSkill
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserSkill extends EntityPathBase<UserSkill> {

    private static final long serialVersionUID = 1409831067L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserSkill userSkill = new QUserSkill("userSkill");

    public final QSkill skill;

    public final NumberPath<Integer> skillLevel = createNumber("skillLevel", Integer.class);

    public final QUser user;

    public final NumberPath<Long> userSkillId = createNumber("userSkillId", Long.class);

    public QUserSkill(String variable) {
        this(UserSkill.class, forVariable(variable), INITS);
    }

    public QUserSkill(Path<? extends UserSkill> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserSkill(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserSkill(PathMetadata metadata, PathInits inits) {
        this(UserSkill.class, metadata, inits);
    }

    public QUserSkill(Class<? extends UserSkill> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.skill = inits.isInitialized("skill") ? new QSkill(forProperty("skill")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

