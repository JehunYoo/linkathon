package com.link.back.service;

// import static com.link.back.common.mapper.ProjectMapper.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import com.link.back.entity.ProjectStatus;
import com.link.back.entity.Team;
import com.link.back.entity.UserTeam;
import com.link.back.redmine.dto.request.CreateProjectRequest;
import com.link.back.redmine.dto.request.CreateUserRequest;
import com.link.back.redmine.service.RedmineProjectService;
import com.link.back.repository.ProjectImageRepository;
import com.link.back.repository.ProjectRepository;
import com.link.back.repository.TeamRepository;
import com.link.back.repository.UserRepository;
import com.link.back.repository.UserTeamRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {

	// TODO: 프로젝트 이미지 관련 로직 검증

	private final ProjectRepository projectRepository;
	private final TeamRepository teamRepository;
	private final ProjectImageRepository projectImageRepository;
	private final RedmineProjectService redmineProjectService;
	private final UserTeamRepository userTeamRepository;

	public void createProject(ProjectRequestDto projectRequestDto) {
		System.out.println(projectRequestDto);
		Team team = teamRepository.getReferenceById(projectRequestDto.teamId());
		ProjectImage projectImage = null;
		if (projectRequestDto.projectImage() != null) {
			projectImage = createProjectImageEntity(projectRequestDto.projectImage());
			projectImageRepository.save(projectImage);
		}
		Project project = createProjectEntity(team, projectImage, projectRequestDto);
		Project save = projectRepository.save(project);

		// 레드마인 프로젝트 생성
		CreateProjectRequest cpr = CreateProjectRequest.builder()
			.name(projectRequestDto.projectName())
			.identifier(projectRequestDto.projectName() + save.getProjectId())
			.build();
		redmineProjectService.createProject(cpr);

		// 레드마인 유저 생성
		List<String> logins = userTeamRepository.findMembersByTeamId(projectRequestDto.teamId())
			.stream()
			.map(userTeam -> {
				redmineProjectService.createUser(new CreateUserRequest(userTeam.getUser()));
				return new CreateUserRequest(userTeam.getUser()).getLogin();
			})
			.collect(Collectors.toList());

		// 레드마인 프로젝트 유저 등록
		redmineProjectService.addProjectMembers(logins, cpr.getIdentifier());
	}

	public Page<ProjectResponseDto> getAllClosedProjects(Pageable pageable) {
		Page<Project> projectPage = projectRepository.findByProjectStatusOrderByLikesDesc(
			ProjectStatus.CLOSED,
			pageable);
		System.out.println(projectPage);
		List<ProjectResponseDto> projectResponseList = projectPage.stream()
			.map(this::toProjectResponseDto)
			.toList();
		System.out.println(projectResponseList);
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
