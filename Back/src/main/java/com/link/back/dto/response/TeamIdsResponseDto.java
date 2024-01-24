package com.link.back.dto.response;

import static java.util.stream.Collectors.*;

import java.util.List;

import com.link.back.entity.UserTeam;

import lombok.Getter;

@Getter
public class TeamIdsResponseDto {

	private final List<Long> teamIds;

	public TeamIdsResponseDto(List<UserTeam> userTeams) {
		this.teamIds = userTeams.stream()
			.map(userTeam -> userTeam.getTeam().getTeamId())
			.collect(toList());
	}
}
