package com.link.back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProjectImage is a Querydsl query type for ProjectImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectImage extends EntityPathBase<ProjectImage> {

    private static final long serialVersionUID = -663257523L;

    public static final QProjectImage projectImage = new QProjectImage("projectImage");

    public final NumberPath<Long> projectImageId = createNumber("projectImageId", Long.class);

    public final StringPath projectImageName = createString("projectImageName");

    public final StringPath projectImageUrl = createString("projectImageUrl");

    public final StringPath projectOriginImageName = createString("projectOriginImageName");

    public QProjectImage(String variable) {
        super(ProjectImage.class, forVariable(variable));
    }

    public QProjectImage(Path<? extends ProjectImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProjectImage(PathMetadata metadata) {
        super(ProjectImage.class, metadata);
    }

}

