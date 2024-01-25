package com.link.back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHackathonImage is a Querydsl query type for HackathonImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHackathonImage extends EntityPathBase<HackathonImage> {

    private static final long serialVersionUID = -286654925L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHackathonImage hackathonImage = new QHackathonImage("hackathonImage");

    public final QHackathon hackathon;

    public final NumberPath<Long> hackathonImageId = createNumber("hackathonImageId", Long.class);

    public final StringPath hackathonImageName = createString("hackathonImageName");

    public final StringPath hackathonImageUrl = createString("hackathonImageUrl");

    public final StringPath hackathonOriginImageName = createString("hackathonOriginImageName");

    public QHackathonImage(String variable) {
        this(HackathonImage.class, forVariable(variable), INITS);
    }

    public QHackathonImage(Path<? extends HackathonImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHackathonImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHackathonImage(PathMetadata metadata, PathInits inits) {
        this(HackathonImage.class, metadata, inits);
    }

    public QHackathonImage(Class<? extends HackathonImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hackathon = inits.isInitialized("hackathon") ? new QHackathon(forProperty("hackathon")) : null;
    }

}

