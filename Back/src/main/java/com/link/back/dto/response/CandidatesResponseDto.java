package com.link.back.dto.response;

import static java.util.stream.Collectors.*;

import java.util.List;

import com.link.back.entity.UserTeam;

import lombok.Getter;

@Getter
public class CandidatesResponseDto {

	private final List<CandidateResponseDto> candidates;

	public CandidatesResponseDto(List<UserTeam> userTeams) {
		candidates = userTeams.stream()
			.map(CandidateResponseDto::new)
			.collect(toList());
	}
}
