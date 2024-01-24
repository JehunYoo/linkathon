package com.link.back.dto.response;

import java.time.LocalDateTime;

import com.link.back.entity.ProjectStatus;

import lombok.Builder;

@Builder
public record ProjectResponseDto(
	Long projectId,
	Long teamId, // Team team,
	Long hackathonId, // Hackathon hackathon,
	String projectName,
	String projectTopic,
	String projectDesc,
	ProjectStatus projectStatus,
	LocalDateTime registeredDate,
	LocalDateTime startDate,
	LocalDateTime endDate,
	String projectUrl,
	Integer hackathonScore,
	Integer likes,
	Boolean winState,
	String deployUrl,

	// ProjectImage projectImage,
	String projectImgName,
	String projectImgUrl,
	String projectOriginImgName
) {
}
