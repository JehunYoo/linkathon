package com.link.back.dto.response;

import java.util.List;

import com.link.back.entity.ProjectStatus;

import lombok.Builder;

@Builder
public record HackathonProceedingProjectResponseDto(
	Long teamId,
	Long projectId,
	String teamName,
	List<String> teamMembers,
	Integer hackathonScore
	// 제출 수
	// 최종 테스트일
) {
}
