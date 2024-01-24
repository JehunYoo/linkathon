package com.link.back.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.link.back.dto.ProjectImageDto;
import com.link.back.dto.request.ProjectRequestDto;
import com.link.back.dto.response.ProjectResponseDto;
import com.link.back.entity.Project;
import com.link.back.entity.ProjectImage;
import com.link.back.entity.ProjectStatus;
import com.link.back.entity.Team;
import com.link.back.repository.ProjectImageRepository;
import com.link.back.repository.ProjectRepository;
import com.link.back.repository.TeamRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {

	// TODO: 프로젝트 이미지 관련 로직 검증

	private final ProjectRepository projectRepository;
	private final TeamRepository teamRepository;
	private final ProjectImageRepository projectImageRepository;

	public void createProject(ProjectRequestDto projectRequestDto) {
		Team team = teamRepository.getReferenceById(projectRequestDto.teamId());
		ProjectImage projectImage = createProjectImageEntity(projectRequestDto.projectImage());
		Project project = createProjectEntity(team, projectImage, projectRequestDto);
		projectRepository.save(project);
	}

	public List<ProjectResponseDto> getAllOpenedProjects(Pageable pageable) {
		Page<Project> projects = projectRepository.findAllByProjectStatusOrderByRegisteredDateDesc(ProjectStatus.CLOSED,
			pageable);
		return projects.stream().map(this::toProjectResponseDto).toList();
	}

	public List<ProjectResponseDto> getPopularProjects() {
		Pageable pageable = PageRequest.of(0, 4); // 상위 4개 프로젝트 조회
		Page<Project> projects = projectRepository.findByProjectStatusOrderByLikesDesc(ProjectStatus.CLOSED, pageable);
		return projects.stream().map(this::toProjectResponseDto).toList();
	}

	public List<ProjectResponseDto> getMyProjects(Long userId) {
		// TODO: 사용자가 갖고 있는 프로젝트 정보 반환
		return null;
	}

	public ProjectResponseDto getProjectDetail(Long projectId) {
		Project project = projectRepository.findById(projectId).orElseThrow();
		return toProjectResponseDto(project);
	}

	public void updateProject(Long projectId, ProjectRequestDto projectRequestDto) {
		Project project = projectRepository.findById(projectId).orElseThrow();
		ProjectImage projectImage = createProjectImageEntity(projectRequestDto.projectImage());
		this.updateProjectEntity(project, projectImage, projectRequestDto);
		projectRepository.save(project);
	}

	public void deleteProject(Long projectId) {
		// TODO: 삭제시 검증 요소 추가
		projectRepository.deleteById(projectId);
	}

	public void submitProject(Long projectId) {
		Project project = projectRepository.findById(projectId).orElseThrow();
		project.updateProjectStatus();
		projectRepository.save(project);
	}

	private ProjectResponseDto toProjectResponseDto(Project project) {
		return ProjectResponseDto.builder()
			.projectId(project.getProjectId())
			.teamId(project.getTeam().getTeamId())
			.hackathonId(project.getTeam().getHackathon().getHackathonId())
			.projectName(project.getProjectName())
			.projectTopic(project.getProjectTopic())
			.projectDesc(project.getProjectDesc())
			.projectStatus(project.getProjectStatus())
			.registeredDate(project.getRegisteredDate())
			.startDate(project.getStartDate())
			.endDate(project.getEndDate())
			.projectUrl(project.getProjectUrl())
			.hackathonScore(project.getHackathon_score())
			.winState(project.getWinState())
			.deployUrl(project.getDeployUrl())
			.projectImgName(project.getProjectImage().getProjectImageName())
			.projectImgUrl(project.getProjectImage().getProjectImageUrl())
			.projectOriginImgName(project.getProjectImage().getProjectOriginImageName())
			.build();
	}

	private Project createProjectEntity(Team team, ProjectImage projectImage, ProjectRequestDto projectRequestDto) {
		return Project.builder()
			.team(team)
			.projectImage(projectImage)
			.projectName(projectRequestDto.projectName())
			.projectTopic(projectRequestDto.projectTopic())
			.projectDesc(projectRequestDto.projectDesc())
			.registeredDate(LocalDateTime.now())
			.startDate(projectRequestDto.startDate())
			.endDate(projectRequestDto.endDate())
			.projectUrl(projectRequestDto.projectUrl())
			// .deployUrl("http://sample-deploy.com")
			.build();
	}

	private void updateProjectEntity(Project project, ProjectImage projectImage, ProjectRequestDto projectRequestDto) {
		project.updateProjectDetail(
			projectRequestDto.projectName(),
			projectRequestDto.projectTopic(),
			projectRequestDto.projectDesc(),
			projectRequestDto.startDate(),
			projectRequestDto.endDate(),
			projectRequestDto.projectUrl(),
			projectRequestDto.deployUrl(),
			projectImage
		);
	}

	public ProjectImage createProjectImageEntity(ProjectImageDto projectImageDto) {
		return projectImageDto == null ? null : ProjectImage.builder()
			.projectImageName(projectImageDto.projectImgName())
			.projectImageUrl(projectImageDto.projectImgUrl())
			.projectOriginImageName(projectImageDto.projectOriginImgName())
			.build();
	}

}
