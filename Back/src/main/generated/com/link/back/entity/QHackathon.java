package com.link.back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHackathon is a Querydsl query type for Hackathon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHackathon extends EntityPathBase<Hackathon> {

    private static final long serialVersionUID = 163872776L;

    public static final QHackathon hackathon = new QHackathon("hackathon");

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Long> hackathonId = createNumber("hackathonId", Long.class);

    public final StringPath hackathonName = createString("hackathonName");

    public final NumberPath<Integer> maxPoint = createNumber("maxPoint", Integer.class);

    public final DatePath<java.time.LocalDate> registerDate = createDate("registerDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> teamDeadlineDate = createDate("teamDeadlineDate", java.time.LocalDate.class);

    public QHackathon(String variable) {
        super(Hackathon.class, forVariable(variable));
    }

    public QHackathon(Path<? extends Hackathon> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHackathon(PathMetadata metadata) {
        super(Hackathon.class, metadata);
    }

}

