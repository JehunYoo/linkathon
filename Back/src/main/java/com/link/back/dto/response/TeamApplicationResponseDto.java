package com.link.back.dto.response;

import static java.util.stream.Collectors.*;

import java.util.List;

import com.link.back.entity.Hackathon;
import com.link.back.entity.Team;
import com.link.back.entity.UserTeam;

import lombok.Getter;

@Getter
public class TeamApplicationResponseDto {

	private final String teamName;
	private final String teamDesc;
	private final HackathonInfoResponseDto hackathonInfoResponseDto;
	private final List<TeamMemberResponseDto> members;
	private final List<TeamListSimpleResponseDto> teams;

	public TeamApplicationResponseDto(List<Team> teams, Team team, List<UserTeam> members) {
		this.teams = teams.stream()
			.map(TeamListSimpleResponseDto::new)
			.collect(toList());
		this.teamName = team.getTeamName();
		this.teamDesc = team.getTeamDesc();
		Hackathon hackathon = team.getHackathon();
		this.hackathonInfoResponseDto =	new HackathonInfoResponseDto(hackathon);
		this.members = members.stream().map(TeamMemberResponseDto::new)
			.collect(toList());
	}
}
