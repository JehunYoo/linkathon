package com.link.back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProject is a Querydsl query type for Project
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProject extends EntityPathBase<Project> {

    private static final long serialVersionUID = -1304832082L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProject project = new QProject("project");

    public final StringPath deployUrl = createString("deployUrl");

    public final DateTimePath<java.time.LocalDateTime> endDate = createDateTime("endDate", java.time.LocalDateTime.class);

    public final QHackathon hackathon;

    public final NumberPath<Integer> hackathon_score = createNumber("hackathon_score", Integer.class);

    public final NumberPath<Integer> likes = createNumber("likes", Integer.class);

    public final StringPath projectDesc = createString("projectDesc");

    public final NumberPath<Long> projectId = createNumber("projectId", Long.class);

    public final QProjectImage projectImage;

    public final StringPath projectName = createString("projectName");

    public final EnumPath<ProjectStatus> projectStatus = createEnum("projectStatus", ProjectStatus.class);

    public final StringPath projectTopic = createString("projectTopic");

    public final StringPath projectUrl = createString("projectUrl");

    public final DateTimePath<java.time.LocalDateTime> registeredDate = createDateTime("registeredDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public final QTeam team;

    public final BooleanPath winState = createBoolean("winState");

    public QProject(String variable) {
        this(Project.class, forVariable(variable), INITS);
    }

    public QProject(Path<? extends Project> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProject(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProject(PathMetadata metadata, PathInits inits) {
        this(Project.class, metadata, inits);
    }

    public QProject(Class<? extends Project> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hackathon = inits.isInitialized("hackathon") ? new QHackathon(forProperty("hackathon")) : null;
        this.projectImage = inits.isInitialized("projectImage") ? new QProjectImage(forProperty("projectImage")) : null;
        this.team = inits.isInitialized("team") ? new QTeam(forProperty("team")) : null;
    }

}

