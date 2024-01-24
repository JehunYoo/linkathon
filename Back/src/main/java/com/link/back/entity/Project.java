package com.link.back.entity;

import static com.link.back.config.AppConstant.*;
import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Project {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long projectId;

	@OneToOne(fetch = LAZY)
	@JoinColumn(name = "team_id", nullable = false)
	private Team team;

	@OneToOne(fetch = LAZY)
	@JoinColumn(name = "project_image_id")
	private ProjectImage projectImage;

	@Column(length = PROJECT_NAME_LENGTH, nullable = false)
	private String projectName;

	@Column(length = PROJECT_TOPIC_LENGTH, nullable = false)
	private String projectTopic;

	@Column(length = PROJECT_DESC_LENGTH)
	private String projectDesc;

	@Enumerated(STRING)
	@Column(nullable = false)
	private ProjectStatus projectStatus = ProjectStatus.OPENED;

	@Column(nullable = false)
	private LocalDateTime registeredDate;

	@Column(nullable = false)
	private LocalDateTime startDate;

	@Column(nullable = false)
	private LocalDateTime endDate;

	@Column(length = PROJECT_URL_LENGTH)
	private String projectUrl;

	@Column(nullable = false)
	private Integer hackathon_score = 0;

	@Column(nullable = false)
	private Boolean winState = Boolean.FALSE;

	@Column(length = PROJECT_DEPLOY_LENGTH)
	private String deployUrl;

	@Builder
	public Project(Long projectId, Team team, ProjectImage projectImage, String projectName, String projectTopic,
		String projectDesc, ProjectStatus projectStatus, LocalDateTime registeredDate, LocalDateTime startDate,
		LocalDateTime endDate, String projectUrl, Integer hackathon_score, Boolean winState, String deployUrl) {
		this.projectId = projectId;
		this.team = team;
		this.projectImage = projectImage;
		this.projectName = projectName;
		this.projectTopic = projectTopic;
		this.projectDesc = projectDesc;
		this.projectStatus = projectStatus;
		this.registeredDate = registeredDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.projectUrl = projectUrl;
		this.hackathon_score = hackathon_score;
		this.winState = winState;
		this.deployUrl = deployUrl;
	}

	public void updateProjectDetail(String projectName, String projectTopic, String projectDesc,
		LocalDateTime startDate, LocalDateTime endDate,
		String projectUrl, String deployUrl, ProjectImage projectImage) {
		this.projectName = projectName;
		this.projectTopic = projectTopic;
		if (projectDesc != null)
			this.projectDesc = projectDesc;
		this.startDate = startDate;
		this.endDate = endDate;
		this.projectUrl = projectUrl;
		if (deployUrl != null)
			this.deployUrl = deployUrl;
		if (projectImage != null)
			this.projectImage = projectImage;
	}

	public void updateProjectStatus() {
		this.projectStatus.nextStatus();
	}

}
