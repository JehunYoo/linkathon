package com.link.back.dto.response;

import com.link.back.entity.Team;

import lombok.Data;
import lombok.Getter;
@Getter
public class TeamListSimpleResponseDto {

	private final Long teamId;
	private final String teamName;

	public TeamListSimpleResponseDto(Team team) {
		this.teamId = team.getTeamId();
		this.teamName = team.getTeamName();
	}

}
