package com.link.back.dto.response;

import com.link.back.entity.UserSkill;

import lombok.Getter;

@Getter
public class SkillResponseDto {

	private final String skillName;
	private final String skillImageUrl;
	private final String skillLevel;
	private final String skillType;

	public SkillResponseDto(UserSkill userSkill) {
		this.skillName = userSkill.getSkill().getSkillName();
		this.skillImageUrl = userSkill.getSkill().getSkillImageUrl();
		this.skillLevel = String.valueOf(userSkill.getSkillLevel());
		this.skillType = userSkill.getSkill().getSkillType().getName();

	}


}
