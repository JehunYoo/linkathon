package com.link.back.dto.response;

import java.time.LocalDateTime;

import com.link.back.entity.ProjectStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WinnerProjectResponseDto {
	private final WinnerProjectInfoDto winnerProjectInfoDto;
	private final TeamResponseDto teamResponseDto;

	@Builder
	public WinnerProjectResponseDto(WinnerProjectInfoDto winnerProjectInfoDto, TeamResponseDto teamResponseDto) {
		this.winnerProjectInfoDto = winnerProjectInfoDto;
		this.teamResponseDto = teamResponseDto;
	}
}
