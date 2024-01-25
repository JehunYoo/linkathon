package com.link.back.dto.response;

import java.time.LocalDateTime;

import com.link.back.dto.ProjectImageDto;
import com.link.back.entity.ProjectStatus;

import lombok.Builder;

@Builder
public record ProjectResponseDto(
	Long projectId,
	Long teamId, // Team team,
	String projectName,
	String projectTopic,
	String projectDesc,
	ProjectStatus projectStatus,
	LocalDateTime registeredDate,
	String projectUrl,
	Integer hackathonScore,
	Integer likes,
	Boolean winState,
	String deployUrl,

	ProjectImageDto projectImage
) {
}
