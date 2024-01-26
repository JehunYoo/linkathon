package com.link.back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHackathon is a Querydsl query type for Hackathon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHackathon extends EntityPathBase<Hackathon> {

    private static final long serialVersionUID = 163872776L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHackathon hackathon = new QHackathon("hackathon");

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Long> hackathonId = createNumber("hackathonId", Long.class);

    public final QHackathonImage hackathonImage;

    public final StringPath hackathonName = createString("hackathonName");

    public final NumberPath<Integer> maxPoint = createNumber("maxPoint", Integer.class);

    public final NumberPath<Integer> maxTeamMember = createNumber("maxTeamMember", Integer.class);

    public final DatePath<java.time.LocalDate> registerDate = createDate("registerDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> teamDeadlineDate = createDate("teamDeadlineDate", java.time.LocalDate.class);

    public QHackathon(String variable) {
        this(Hackathon.class, forVariable(variable), INITS);
    }

    public QHackathon(Path<? extends Hackathon> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHackathon(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHackathon(PathMetadata metadata, PathInits inits) {
        this(Hackathon.class, metadata, inits);
    }

    public QHackathon(Class<? extends Hackathon> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hackathonImage = inits.isInitialized("hackathonImage") ? new QHackathonImage(forProperty("hackathonImage"), inits.get("hackathonImage")) : null;
    }

}

