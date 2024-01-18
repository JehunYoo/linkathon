package com.link.back.entity;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class UserSkill {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long userSkillId;

	private Integer skillLevel; //스킬에 대한 경력

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "skill_id", nullable = false)
	private Skill skill;
}
