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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
	private ProjectStatus projectStatus;

	@Column(nullable = false)
	private LocalDateTime registeredDate;

	@Column(nullable = false)
	private LocalDateTime startDate;

	@Column(nullable = false)
	private LocalDateTime endDate;

	@Column(length = PROJECT_URL_LENGTH)
	private String projectUrl;

	@Column(nullable = false)
	private Integer hackathon_score;

	@Column(nullable = false)
	private Integer likes;

	@Column(nullable = false)
	private boolean winState;

	@Column(length = PROJECT_DEPLOY_LENGTH)
	private String deployUrl;
}
