package com.link.back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProjectContribution is a Querydsl query type for ProjectContribution
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectContribution extends EntityPathBase<ProjectContribution> {

    private static final long serialVersionUID = -1475560738L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProjectContribution projectContribution = new QProjectContribution("projectContribution");

    public final StringPath author = createString("author");

    public final NumberPath<Integer> commits = createNumber("commits", Integer.class);

    public final NumberPath<Integer> deletions = createNumber("deletions", Integer.class);

    public final NumberPath<Integer> insertions = createNumber("insertions", Integer.class);

    public final QProject project;

    public final NumberPath<Long> projectContributionId = createNumber("projectContributionId", Long.class);

    public final NumberPath<Double> ratioInChanges = createNumber("ratioInChanges", Double.class);

    public final NumberPath<Double> ratioInComments = createNumber("ratioInComments", Double.class);

    public final NumberPath<Integer> totalRows = createNumber("totalRows", Integer.class);

    public QProjectContribution(String variable) {
        this(ProjectContribution.class, forVariable(variable), INITS);
    }

    public QProjectContribution(Path<? extends ProjectContribution> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProjectContribution(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProjectContribution(PathMetadata metadata, PathInits inits) {
        this(ProjectContribution.class, metadata, inits);
    }

    public QProjectContribution(Class<? extends ProjectContribution> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project"), inits.get("project")) : null;
    }

}

