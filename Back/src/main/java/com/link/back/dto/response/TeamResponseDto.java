package com.link.back.dto.response;

import static java.util.stream.Collectors.*;

import java.util.List;

import com.link.back.entity.Team;
import com.link.back.entity.UserTeam;

import lombok.Getter;

@Getter
public class TeamResponseDto {

	private final String teamName;
	private final Integer teamMember;
	private final String teamDesc;
	private final List<TeamSkillDto> teamSkills;
	private final List<TeamMemberResponseDto> members;

	public TeamResponseDto(Team team, List<UserTeam> members) {
		this.teamName = team.getTeamName();
		this.teamMember = team.getTeamMember();
		this.teamDesc = team.getTeamDesc();
		this.teamSkills = team.getTeamSkills().stream()
			.map(TeamSkillDto::new).collect(toList());
		this.members = members.stream()
			.map(TeamMemberResponseDto::new).collect(toList());
	}

}
