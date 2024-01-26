package com.link.back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBackPerformance is a Querydsl query type for BackPerformance
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBackPerformance extends EntityPathBase<BackPerformance> {

    private static final long serialVersionUID = 1045599326L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBackPerformance backPerformance = new QBackPerformance("backPerformance");

    public final NumberPath<Long> backPerformanceId = createNumber("backPerformanceId", Long.class);

    public final StringPath fileName = createString("fileName");

    public final NumberPath<Integer> line = createNumber("line", Integer.class);

    public final StringPath message = createString("message");

    public final QProject project;

    public final StringPath severity = createString("severity");

    public final StringPath type = createString("type");

    public QBackPerformance(String variable) {
        this(BackPerformance.class, forVariable(variable), INITS);
    }

    public QBackPerformance(Path<? extends BackPerformance> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBackPerformance(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBackPerformance(PathMetadata metadata, PathInits inits) {
        this(BackPerformance.class, metadata, inits);
    }

    public QBackPerformance(Class<? extends BackPerformance> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project"), inits.get("project")) : null;
    }

}

