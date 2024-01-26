package com.link.back.dto.request;

import static java.util.stream.Collectors.*;

import java.util.List;

import com.link.back.entity.Team;
import com.link.back.entity.TeamSkill;

import lombok.Getter;

@Getter
public class UpdateTeamRequestDto {

	private String teamName;
	private String teamDesc;
	private List<TeamSkill> teamSkillSets;

}
