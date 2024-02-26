package com.link.back.service;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.link.back.dto.response.BackPerformanceMessageResponseDto;
import com.link.back.dto.response.BackPerformanceResponseDto;
import com.link.back.dto.response.HackathonsResponseDto;
import com.link.back.entity.BackPerformance;
import com.link.back.entity.BackPerformanceMessage;
import com.link.back.entity.Hackathon;
import com.link.back.entity.Project;
import com.link.back.repository.BackPerformanceMessageRepository;
import com.link.back.repository.BackPerformanceRepository;
import com.link.back.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BackPerformanceService {
	private final BackPerformanceRepository backPerformanceRepository;
	private final BackPerformanceMessageRepository backPerformanceMessageRepository;
	private final ProjectRepository projectRepository;
	public Page<BackPerformanceResponseDto> getBackPerformanceResponses(Long projectId, Pageable pageable) {
		Project project = projectRepository.getReferenceById(projectId);
		// Optional<Page<BackPerformance>> optBackPerformances = Optional.of(backPerformanceRepository.findByProjectId(projectId,pageable).orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 존재하지 않습니다")));
		Page<BackPerformance> optBackPerformances = backPerformanceRepository.findByProject(project,pageable);

		// List<BackPerformanceResponseDto> backPerformanceResponseDtos = optBackPerformances.get().getContent().stream()
		// 	.map(this::mapToBackPerformanceResponseDto)
		// 	.toList();
		List<BackPerformanceResponseDto> backPerformanceResponseDtos = optBackPerformances.getContent().stream()
			.map(this::mapToBackPerformanceResponseDto)
			.toList();
		return new PageImpl<>(backPerformanceResponseDtos,pageable,optBackPerformances.getTotalElements());
	}

	public BackPerformanceMessageResponseDto getMessageCount(Long projectId) {
		Map<String,Integer> counts = backPerformanceMessageRepository.countBackPerformanceMessageByMessageStartsWith(projectId);
		return BackPerformanceMessageResponseDto.builder().addCount(Integer.parseInt(String.valueOf(counts.get("AddCount")))).removeCount(Integer.parseInt(String.valueOf(counts.get("RemoveCount")))).changeCount(Integer.parseInt(String.valueOf(counts.get("ChangeCount")))).completeCount(Integer.parseInt(String.valueOf(counts.get("CompleteCount")))).makeCount(Integer.parseInt(String.valueOf(counts.get("MakeCount"))))
			.mergeCount(Integer.parseInt(String.valueOf(counts.get("MergeCount")))).moveCount(Integer.parseInt(String.valueOf(counts.get("MoveCount")))).refactorCount(Integer.parseInt(String.valueOf(counts.get("RefactorCount")))).replaceCount(Integer.parseInt(String.valueOf(counts.get("ReplaceCount")))).etcCount(Integer.parseInt(String.valueOf(counts.get("EtcCount")))).build();

	}
	private BackPerformanceResponseDto mapToBackPerformanceResponseDto(BackPerformance backPerformance) {
		return BackPerformanceResponseDto.builder().backPerformanceId(backPerformance.getBackPerformanceId())
			.bugs(backPerformance.getBugs()).coverage(backPerformance.getCoverage())
			.duplications(backPerformance.getDuplications()).codeSmells(backPerformance.getCodeSmells())
			.securityRating(backPerformance.getSecurityRating()).vulnerabilities(backPerformance.getVulnerabilities())
			.build();
	}

}
