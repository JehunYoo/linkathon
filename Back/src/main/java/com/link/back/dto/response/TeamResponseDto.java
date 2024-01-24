package com.link.back.dto.response;

import com.link.back.entity.Team;
import com.link.back.entity.TeamStatus;

import lombok.Getter;

@Getter
public class TeamResponseDto {

	private final String teamName;
	private final TeamStatus teamStatus;
	private final Integer teamMember;
	private final String teamDesc;

	public TeamResponseDto(Team team) {
		this.teamName = team.getTeamName();
		this.teamStatus = team.getTeamStatus();
		this.teamMember = team.getTeamMember();
		this.teamDesc = team.getTeamDesc();
	}

}
