package com.link.back.service;

// import static com.link.back.common.mapper.ProjectMapper.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.link.back.dto.ProjectImageDto;
import com.link.back.dto.request.ProjectRequestDto;
import com.link.back.dto.response.ProjectResponseDto;
import com.link.back.entity.Project;
import com.link.back.entity.ProjectImage;
import com.link.back.entity.ProjectLike;
import com.link.back.entity.ProjectStatus;
import com.link.back.entity.Team;
import com.link.back.entity.User;
import com.link.back.repository.ProjectImageRepository;
import com.link.back.repository.ProjectLikeRepository;
import com.link.back.repository.ProjectRepository;
import com.link.back.repository.TeamRepository;
import com.link.back.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {

	// TODO: 프로젝트 이미지 관련 로직 검증

	private final ProjectRepository projectRepository;
	private final TeamRepository teamRepository;
	private final ProjectImageRepository projectImageRepository;
	private final ProjectLikeRepository projectLikeRepository;
	private final UserRepository userRepository;

	public void createProject(ProjectRequestDto projectRequestDto) {
		Team team = teamRepository.getReferenceById(projectRequestDto.teamId());
		ProjectImage projectImage = null;
		if (projectRequestDto.projectImage() != null) {
			projectImage = createProjectImageEntity(projectRequestDto.projectImage());
			projectImageRepository.save(projectImage);
		}
		Project project = createProjectEntity(team, projectImage, projectRequestDto);
		projectRepository.save(project);
	}

	public Page<ProjectResponseDto> getAllClosedProjects(Pageable pageable) {
		Page<Project> projectPage = projectRepository.findByProjectStatusOrderByLikesDesc(
			ProjectStatus.CLOSED,
			pageable);
		List<ProjectResponseDto> projectResponseList = projectPage.stream()
			.map(this::toProjectResponseDto)
			.toList();
		return new PageImpl<>(projectResponseList, projectPage.getPageable(), projectPage.getTotalElements());
	}

	public List<ProjectResponseDto> getPopularProjects() {
		Pageable pageable = PageRequest.of(0, 4); // 상위 4개 프로젝트 조회
		Page<Project> projects = projectRepository.findByProjectStatusOrderByLikesDesc(ProjectStatus.CLOSED, pageable);
		return projects.stream().map(this::toProjectResponseDto).toList();
	}

	public List<ProjectResponseDto> getMyProjects(Long userId) {
		return projectRepository.findByUserId(userId)
			.stream()
			.map(this::toProjectResponseDto)
			.toList();
	}

	public ProjectResponseDto getProjectDetail(Long projectId) {
		Project project = projectRepository.findById(projectId).orElseThrow();
		return toProjectResponseDto(project);
	}

	public void updateProject(Long projectId, ProjectRequestDto projectRequestDto) {
		Project project = projectRepository.findById(projectId).orElseThrow();
		ProjectImage projectImage = null;
		if (projectRequestDto.projectImage() != null) {
			projectImage = createProjectImageEntity(projectRequestDto.projectImage());
			projectImageRepository.save(projectImage);
		}
		updateProjectEntity(project, projectImage, projectRequestDto);
		projectRepository.save(project);
	}

	public void deleteProject(Long projectId) {
		projectRepository.deleteById(projectId);
	}

	public void submitProject(Long projectId) {
		Project project = projectRepository.findById(projectId).orElseThrow();
		project.updateProjectStatus();
		projectRepository.save(project);
	}

	public void registerLike(Long userId, Long projectId) {
		User user = userRepository.getReferenceById(userId);
		Project project = projectRepository.getReferenceById(projectId);
		Optional<ProjectLike> projectLikeOptional = projectLikeRepository.findByProjectAndUser(project, user);
		if (projectLikeOptional.isEmpty()) {
			ProjectLike newProjectLike = ProjectLike.builder()
				.user(user)
				.project(project)
				.build();
			projectLikeRepository.save(newProjectLike);
		} else {
			throw new RuntimeException(); // FIXME: 예외 처리 필요
		}
	}

	public void unregisterLike(Long userId, Long projectId) {
		User user = userRepository.getReferenceById(userId);
		Project project = projectRepository.getReferenceById(projectId);
		Optional<ProjectLike> projectLikeOptional = projectLikeRepository.findByProjectAndUser(project, user);
		if (projectLikeOptional.isPresent()) {
			projectLikeRepository.delete(projectLikeOptional.get());
		} else {
			throw new RuntimeException(); // FIXME: 예외 처리 필요
		}
	}

	public List<ProjectResponseDto> getLikedProjects(Long userId) {
		User user = userRepository.getReferenceById(userId);
		List<Project> projects = projectRepository.findLikedProjectsByUser(user);
		return projects.stream().map(this::toProjectResponseDto).toList();
	}

	private ProjectResponseDto toProjectResponseDto(Project project) {
		return ProjectResponseDto.builder()
			.projectId(project.getProjectId())
			.teamId(project.getTeam().getTeamId())
			.hackathonId(project.getTeam().getHackathon().getHackathonId())
			.hackathonName(project.getTeam().getHackathon().getHackathonName())
			.hackathonScore(project.getHackathonScore())
			.projectName(project.getProjectName())
			.projectTopic(project.getProjectTopic())
			.projectDesc(project.getProjectDesc())
			.projectStatus(project.getProjectStatus())
			.registeredDate(project.getRegisteredDate())
			.projectUrl(project.getProjectUrl())
			.winState(project.getWinState())
			.deployUrl(project.getDeployUrl())
			.projectImage(
				project.getProjectImage() == null ? null :
					toProjectImageDto(project.getProjectImage())
			)
			.build();
	}

	private ProjectImageDto toProjectImageDto(ProjectImage projectImage) {
		return ProjectImageDto.builder()
			.projectImgName(projectImage.getProjectImageName())
			.projectImgUrl(projectImage.getProjectImageUrl())
			.projectOriginImgName(projectImage.getProjectOriginImageName())
			.build();
	}

	private Project createProjectEntity(Team team, ProjectImage projectImage, ProjectRequestDto projectRequestDto) {
		return Project.builder()
			.team(team)
			.projectImage(projectImage)
			.projectName(projectRequestDto.projectName())
			.projectTopic(projectRequestDto.projectTopic())
			.projectDesc(projectRequestDto.projectDesc())
			.projectUrl(projectRequestDto.projectUrl())
			.registeredDate(LocalDateTime.now())
			.hackathonScore(0)
			.winState(Boolean.FALSE)
			.projectStatus(ProjectStatus.OPENED)
			// .deployUrl("http://sample-deploy.com")
			.build();
	}

	private void updateProjectEntity(Project project, ProjectImage projectImage, ProjectRequestDto projectRequestDto) {
		project.updateProjectDetail(
			projectRequestDto.projectName(),
			projectRequestDto.projectTopic(),
			projectRequestDto.projectDesc(),
			projectRequestDto.projectUrl(),
			projectRequestDto.deployUrl(),
			projectImage
		);
	}

	private ProjectImage createProjectImageEntity(ProjectImageDto projectImageDto) {
		return ProjectImage.builder()
			.projectImageName(projectImageDto.projectImgName())
			.projectImageUrl(projectImageDto.projectImgUrl())
			.projectOriginImageName(projectImageDto.projectOriginImgName())
			.build();
	}

}
