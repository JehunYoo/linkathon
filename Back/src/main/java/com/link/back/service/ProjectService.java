// package com.link.back.service;
//
// // import static com.link.back.common.mapper.ProjectMapper.*;
//
// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.stream.Collectors;
// import java.util.Optional;
//
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageImpl;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
// import org.springframework.stereotype.Service;
//
// import com.link.back.dto.ProjectImageDto;
// import com.link.back.dto.request.ProjectRequestDto;
// import com.link.back.dto.response.ProjectResponseDto;
// import com.link.back.entity.Project;
// import com.link.back.entity.ProjectImage;
// import com.link.back.entity.ProjectLike;
// import com.link.back.entity.ProjectStatus;
// import com.link.back.entity.Team;
// import com.link.back.entity.UserTeam;
// import com.link.back.redmine.dto.request.CreateProjectRequest;
// import com.link.back.redmine.dto.request.CreateUserRequest;
// import com.link.back.redmine.service.RedmineProjectService;
// import com.link.back.entity.User;
// import com.link.back.repository.ProjectImageRepository;
// import com.link.back.repository.ProjectLikeRepository;
// import com.link.back.repository.ProjectRepository;
// import com.link.back.repository.TeamRepository;
// import com.link.back.repository.UserRepository;
// import com.link.back.repository.UserTeamRepository;
//
// import lombok.RequiredArgsConstructor;
//
// @Service
// @RequiredArgsConstructor
// public class ProjectService {
//
// 	// TODO: 프로젝트 이미지 관련 로직 검증
//
// 	private final ProjectRepository projectRepository;
// 	private final TeamRepository teamRepository;
// 	private final ProjectImageRepository projectImageRepository;
// 	private final RedmineProjectService redmineProjectService;
// 	private final UserTeamRepository userTeamRepository;
// 	private final ProjectLikeRepository projectLikeRepository;
// 	private final UserRepository userRepository;
//
// 	public void createProject(ProjectRequestDto projectRequestDto) {
// 		Team team = teamRepository.getReferenceById(projectRequestDto.teamId());
// 		ProjectImage projectImage = null;
// 		if (projectRequestDto.projectImage() != null) {
// 			projectImage = createProjectImageEntity(projectRequestDto.projectImage());
// 			projectImageRepository.save(projectImage);
// 		}
// 		Project project = createProjectEntity(team, projectImage, projectRequestDto);
// 		Project save = projectRepository.save(project);
//
// 		// 레드마인 프로젝트 생성
// 		CreateProjectRequest cpr = CreateProjectRequest.builder()
// 			.name(projectRequestDto.projectName())
// 			.identifier(projectRequestDto.projectName() + save.getProjectId())
// 			.build();
// 		redmineProjectService.createProject(cpr);
//
// 		// 레드마인 유저 생성
// 		List<String> logins = userTeamRepository.findMembersByTeamId(projectRequestDto.teamId())
// 			.stream()
// 			.map(userTeam -> {
// 				redmineProjectService.createUser(new CreateUserRequest(userTeam.getUser()));
// 				return new CreateUserRequest(userTeam.getUser()).getLogin();
// 			})
// 			.collect(Collectors.toList());
//
// 		// 레드마인 프로젝트 유저 등록
// 		redmineProjectService.addProjectMembers(logins, cpr.getIdentifier());
// 	}
//
// 	public Page<ProjectResponseDto> getAllClosedProjects(Long userId, Pageable pageable) {
// 		User user = userId != null ? userRepository.getReferenceById(userId) : null;
// 		Page<Project> projectPage = projectRepository.findByProjectStatusOrderByLikesDesc(
// 			ProjectStatus.CLOSED,
// 			pageable);
// 		List<ProjectResponseDto> projectResponseList = projectPage.stream()
// 			.map(p -> toProjectResponseDto(p, user))
// 			.toList();
// 		return new PageImpl<>(projectResponseList, projectPage.getPageable(), projectPage.getTotalElements());
// 	}
//
// 	public List<ProjectResponseDto> getPopularProjects(Long userId) {
// 		User user = userId != null ? userRepository.getReferenceById(userId) : null;
// 		Pageable pageable = PageRequest.of(0, 4); // 상위 4개 프로젝트 조회
// 		Page<Project> projects = projectRepository.findByProjectStatusOrderByLikesDesc(ProjectStatus.CLOSED, pageable);
// 		return projects.stream().map(p -> toProjectResponseDto(p, user)).toList();
// 	}
//
// 	public List<ProjectResponseDto> getMyProjects(Long userId) {
// 		User user = userId != null ? userRepository.getReferenceById(userId) : null;
// 		return projectRepository.findByUserId(userId)
// 			.stream()
// 			.map(p -> toProjectResponseDto(p, user))
// 			.toList();
// 	}
//
// 	public ProjectResponseDto getProjectDetail(Long userId, Long projectId) {
// 		User user = userId != null ? userRepository.getReferenceById(userId) : null;
// 		Project project = projectRepository.findById(projectId).orElseThrow();
// 		return toProjectResponseDto(project, user);
// 	}
//
// 	public void updateProject(Long projectId, ProjectRequestDto projectRequestDto) {
// 		Project project = projectRepository.findById(projectId).orElseThrow();
// 		ProjectImage projectImage = null;
// 		if (projectRequestDto.projectImage() != null) {
// 			projectImage = createProjectImageEntity(projectRequestDto.projectImage());
// 			projectImageRepository.save(projectImage);
// 		}
// 		updateProjectEntity(project, projectImage, projectRequestDto);
// 		projectRepository.save(project);
// 	}
//
// 	public void deleteProject(Long projectId) {
// 		projectRepository.deleteById(projectId);
// 	}
//
// 	public void submitProject(Long projectId) {
// 		Project project = projectRepository.findById(projectId).orElseThrow();
// 		project.updateProjectStatus();
// 		projectRepository.save(project);
// 	}
//
// 	public void registerLike(Long userId, Long projectId) {
// 		User user = userRepository.getReferenceById(userId);
// 		Project project = projectRepository.getReferenceById(projectId);
// 		Optional<ProjectLike> projectLikeOptional = projectLikeRepository.findByProjectAndUser(project, user);
// 		if (projectLikeOptional.isEmpty()) {
// 			ProjectLike newProjectLike = ProjectLike.builder()
// 				.user(user)
// 				.project(project)
// 				.build();
// 			projectLikeRepository.save(newProjectLike);
// 		} else {
// 			throw new RuntimeException(); // FIXME: 예외 처리 필요
// 		}
// 	}
//
// 	public void unregisterLike(Long userId, Long projectId) {
// 		User user = userRepository.getReferenceById(userId);
// 		Project project = projectRepository.getReferenceById(projectId);
// 		Optional<ProjectLike> projectLikeOptional = projectLikeRepository.findByProjectAndUser(project, user);
// 		if (projectLikeOptional.isPresent()) {
// 			projectLikeRepository.delete(projectLikeOptional.get());
// 		} else {
// 			throw new RuntimeException(); // FIXME: 예외 처리 필요
// 		}
// 	}
//
// 	public List<ProjectResponseDto> getLikedProjects(Long userId) {
// 		User user = userRepository.getReferenceById(userId);
// 		List<Project> projects = projectRepository.findLikedProjectsByUser(user);
// 		return projects.stream().map(p -> toProjectResponseDto(p, user)).toList();
// 	}
//
// 	private ProjectResponseDto toProjectResponseDto(Project project, User user) {
// 		return ProjectResponseDto.builder()
// 			.projectId(project.getProjectId())
// 			.teamId(project.getTeam().getTeamId())
// 			.hackathonId(project.getTeam().getHackathon().getHackathonId())
// 			.hackathonName(project.getTeam().getHackathon().getHackathonName())
// 			.hackathonTopic("asd")
// 			.hackathonScore(project.getHackathonScore())
// 			.projectName(project.getProjectName())
// 			.projectDesc(project.getProjectDesc())
// 			.projectStatus(project.getProjectStatus())
// 			.registeredDate(project.getRegisteredDate())
// 			.projectUrl(project.getProjectUrl())
// 			.deployUrl(project.getDeployUrl())
// 			.winState(project.getWinState())
// 			.projectImage(
// 				project.getProjectImage() == null ? null :
// 					toProjectImageDto(project.getProjectImage())
// 			)
// 			.starCount(projectLikeRepository.countByProject(project))
// 			.starred(projectLikeRepository.findByProjectAndUser(project, user).isPresent())
// 			.build();
// 	}
//
// 	private ProjectImageDto toProjectImageDto(ProjectImage projectImage) {
// 		return ProjectImageDto.builder()
// 			.projectImgName(projectImage.getProjectImageName())
// 			.projectImgUrl(projectImage.getProjectImageUrl())
// 			.projectOriginImgName(projectImage.getProjectOriginImageName())
// 			.build();
// 	}
//
// 	private Project createProjectEntity(Team team, ProjectImage projectImage, ProjectRequestDto projectRequestDto) {
// 		return Project.builder()
// 			.team(team)
// 			.projectImage(projectImage)
// 			.projectName(projectRequestDto.projectName())
// 			.projectDesc(projectRequestDto.projectDesc())
// 			.projectStatus(ProjectStatus.OPENED)
// 			.registeredDate(LocalDateTime.now())
// 			.projectUrl(projectRequestDto.projectUrl())
// 			.hackathonScore(0)
// 			.winState(Boolean.FALSE)
// 			.deployUrl(null)
// 			.build();
// 	}
//
// 	private void updateProjectEntity(Project project, ProjectImage projectImage, ProjectRequestDto projectRequestDto) {
// 		project.updateProjectDetail(
// 			projectRequestDto.projectName(),
// 			projectRequestDto.projectDesc(),
// 			projectRequestDto.projectUrl(),
// 			projectRequestDto.deployUrl(),
// 			projectImage
// 		);
// 	}
//
// 	private ProjectImage createProjectImageEntity(ProjectImageDto projectImageDto) {
// 		return ProjectImage.builder()
// 			.projectImageName(projectImageDto.projectImgName())
// 			.projectImageUrl(projectImageDto.projectImgUrl())
// 			.projectOriginImageName(projectImageDto.projectOriginImgName())
// 			.build();
// 	}
//
// }
