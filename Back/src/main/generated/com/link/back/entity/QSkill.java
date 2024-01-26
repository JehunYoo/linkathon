package com.link.back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSkill is a Querydsl query type for Skill
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSkill extends EntityPathBase<Skill> {

    private static final long serialVersionUID = -1411090394L;

    public static final QSkill skill = new QSkill("skill");

    public final NumberPath<Long> skillId = createNumber("skillId", Long.class);

    public final StringPath skillImageUrl = createString("skillImageUrl");

    public final StringPath skillName = createString("skillName");

    public final EnumPath<SkillType> skillType = createEnum("skillType", SkillType.class);

    public QSkill(String variable) {
        super(Skill.class, forVariable(variable));
    }

    public QSkill(Path<? extends Skill> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSkill(PathMetadata metadata) {
        super(Skill.class, metadata);
    }

}

