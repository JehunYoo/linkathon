package com.link.back.entity;

import static com.link.back.config.AppConstant.*;
import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Skill {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long skillId;

	@Column(nullable = false, length = SKILL_NAME_LENGTH)
	private String skillName;

	@Column(nullable = false, length = SKILL_IMAGE_URL_LENGTH)
	private String skillImageUrl;

	@Enumerated(STRING)
	private SkillType skillType;
}
