package com.link.back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTeamSkill is a Querydsl query type for TeamSkill
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeamSkill extends EntityPathBase<TeamSkill> {

    private static final long serialVersionUID = 894584137L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTeamSkill teamSkill = new QTeamSkill("teamSkill");

    public final QSkill skill;

    public final QTeam team;

    public final NumberPath<Long> teamSkillId = createNumber("teamSkillId", Long.class);

    public QTeamSkill(String variable) {
        this(TeamSkill.class, forVariable(variable), INITS);
    }

    public QTeamSkill(Path<? extends TeamSkill> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTeamSkill(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTeamSkill(PathMetadata metadata, PathInits inits) {
        this(TeamSkill.class, metadata, inits);
    }

    public QTeamSkill(Class<? extends TeamSkill> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.skill = inits.isInitialized("skill") ? new QSkill(forProperty("skill")) : null;
        this.team = inits.isInitialized("team") ? new QTeam(forProperty("team"), inits.get("team")) : null;
    }

}

