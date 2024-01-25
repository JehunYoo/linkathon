package com.link.back.dto.response;

import com.link.back.entity.Skill;
import com.link.back.entity.UserSkill;

import lombok.Getter;

@Getter
public class SkillDto {

	private final String skillName;
	private final int SkillLevel;
	private final String skillImageUrl;

	public SkillDto(UserSkill userSkill) {
		Skill skill = userSkill.getSkill();
		this.skillName = skill.getSkillName();
		this.SkillLevel = userSkill.getSkillLevel();
		this.skillImageUrl = skill.getSkillImageUrl();
	}

}
