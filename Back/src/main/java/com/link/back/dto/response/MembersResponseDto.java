package com.link.back.dto.response;

import static java.util.stream.Collectors.*;

import java.util.List;

import com.link.back.entity.UserTeam;

import lombok.Getter;

@Getter
public class MembersResponseDto {

	private final List<MemberResponseDto> members;

	public MembersResponseDto(List<UserTeam> userTeams) {
		members = userTeams.stream()
			.map(MemberResponseDto::new)
			.collect(toList());
	}
}
