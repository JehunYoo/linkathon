package com.link.back.dto.response;

import java.util.List;

import com.link.back.entity.UserTeam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MypageConditionDto {

	private boolean applied;
	private boolean suggested;
	private boolean project;
	private boolean team;

	public MypageConditionDto(List<UserTeam> userTeamList, boolean hasProject) {
		userTeamList.stream()
			.map(UserTeam::getMemberStatus)
			.map(Enum::name)
			.forEach(status -> {
				switch (status) {
					case "JOINED" -> team = true;
					case "SUGGESTED" -> suggested = true;
					case "APPLIED" -> applied = true;
				}
			});
		this.project = hasProject;
	}
}
