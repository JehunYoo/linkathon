package com.link.back.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WinnerProjectInfoDto {
	private final Long projectId;
	private final Long teamId;
	private final String projectName;
	private final String projectDesc;
	private final String imgSrc;

	@Builder
	public WinnerProjectInfoDto(Long projectId, Long teamId, String projectName, String projectDesc, String imgSrc) {
		this.projectId = projectId;
		this.teamId = teamId;
		this.projectName = projectName;
		this.projectDesc = projectDesc;
		this.imgSrc = imgSrc;
	}
}
