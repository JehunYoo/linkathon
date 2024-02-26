package com.link.back.dto.response;

import static com.link.back.config.AppConstant.*;

import com.link.back.entity.Skill;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillTypeResponseDto {

	private Long skillId;
	private String skillName;
	private String skillImageUrl;

	public SkillTypeResponseDto(Skill skill) {
		this.skillId = skill.getSkillId();
		this.skillName = skill.getSkillName();
		this.skillImageUrl = skill.getSkillImageUrl();
	}

}
