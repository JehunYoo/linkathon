package com.link.back.dto.request;

import com.link.back.dto.ProjectImageDto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ProjectRequestDto(
	@NotNull Long teamId, // Team team,
	String projectName,
	String projectDesc,
	String projectUrl,
	String deployUrl
) {
}
