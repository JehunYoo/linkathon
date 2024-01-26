package com.link.back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFrontPerformance is a Querydsl query type for FrontPerformance
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFrontPerformance extends EntityPathBase<FrontPerformance> {

    private static final long serialVersionUID = -1727438510L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFrontPerformance frontPerformance = new QFrontPerformance("frontPerformance");

    public final NumberPath<Integer> accessibility = createNumber("accessibility", Integer.class);

    public final NumberPath<Integer> bestPractices = createNumber("bestPractices", Integer.class);

    public final NumberPath<Long> frontPerformanceId = createNumber("frontPerformanceId", Long.class);

    public final NumberPath<Integer> performance = createNumber("performance", Integer.class);

    public final QProject project;

    public final NumberPath<Integer> pwa = createNumber("pwa", Integer.class);

    public final NumberPath<Integer> seo = createNumber("seo", Integer.class);

    public QFrontPerformance(String variable) {
        this(FrontPerformance.class, forVariable(variable), INITS);
    }

    public QFrontPerformance(Path<? extends FrontPerformance> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFrontPerformance(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFrontPerformance(PathMetadata metadata, PathInits inits) {
        this(FrontPerformance.class, metadata, inits);
    }

    public QFrontPerformance(Class<? extends FrontPerformance> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project"), inits.get("project")) : null;
    }

}

