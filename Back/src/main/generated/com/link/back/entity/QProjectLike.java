package com.link.back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProjectLike is a Querydsl query type for ProjectLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectLike extends EntityPathBase<ProjectLike> {

    private static final long serialVersionUID = -852593563L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProjectLike projectLike = new QProjectLike("projectLike");

    public final NumberPath<Long> likeId = createNumber("likeId", Long.class);

    public final QProject project;

    public final QUser user;

    public QProjectLike(String variable) {
        this(ProjectLike.class, forVariable(variable), INITS);
    }

    public QProjectLike(Path<? extends ProjectLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProjectLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProjectLike(PathMetadata metadata, PathInits inits) {
        this(ProjectLike.class, metadata, inits);
    }

    public QProjectLike(Class<? extends ProjectLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project"), inits.get("project")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

