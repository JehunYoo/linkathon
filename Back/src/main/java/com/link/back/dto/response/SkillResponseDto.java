package com.link.back.dto.response;

import com.link.back.entity.Skill;
import com.link.back.entity.UserSkill;

import lombok.Getter;

@Getter
public class SkillResponseDto {

	private final String skillName;

	private final String skillImageUrl;

	public SkillResponseDto(UserSkill userSkill) {
		Skill skill = userSkill.getSkill();

		this.skillName = skill.getSkillName();
		this.skillImageUrl = skill.getSkillImageUrl();
	}
}
