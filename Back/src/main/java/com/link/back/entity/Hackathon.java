package com.link.back.entity;

import static com.link.back.config.AppConstant.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Hackathon {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long hackathonId;

	@Column(nullable = false, length = HACKATHON_NAME_LENGTH)
	private String hackathonName;

	@Column(nullable = false, length = PROJECT_TOPIC_LENGTH)
	private String hackathonTopic;

	@OneToOne(fetch = LAZY)
	@JoinColumn(name = "hackathon_image_id")
	private HackathonImage hackathonImage;

	@Column(nullable = false)
	private LocalDate registerDate;

	@Column(nullable = false)
	private LocalDate teamDeadlineDate;

	@Column(nullable = false)
	private LocalDate startDate;

	@Column(nullable = false)
	private LocalDate endDate;

	@Column(nullable = false)
	private Integer maxPoint;

	@Column(nullable = false)
	private Integer maxTeamMember;

	@Builder
	public Hackathon(String hackathonName, String hackathonTopic, LocalDate registerDate, LocalDate teamDeadlineDate,
		LocalDate startDate,
		LocalDate endDate, Integer maxPoint, Integer maxTeamMember, HackathonImage hackathonImage) {
		this.hackathonName = hackathonName;
		this.hackathonTopic = hackathonTopic;
		this.registerDate = registerDate;
		this.teamDeadlineDate = teamDeadlineDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.maxPoint = maxPoint;
		this.maxTeamMember = maxTeamMember;
		this.hackathonImage = hackathonImage;
	}

	public void updateHackathonInfo(String hackathonName, String hackathonTopic, LocalDate teamDeadlineDate,
		LocalDate startDate,
		LocalDate endDate, Integer maxPoint, Integer maxTeamMember) {
		this.hackathonName = hackathonName;
		this.hackathonTopic = hackathonTopic;
		this.teamDeadlineDate = teamDeadlineDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.maxPoint = maxPoint;
		this.maxTeamMember = maxTeamMember;

	}
}
