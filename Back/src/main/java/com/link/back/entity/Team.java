package com.link.back.entity;

import static com.link.back.config.AppConstant.*;
import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;
import static lombok.AccessLevel.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Team {

	@Id
	@GeneratedValue
	private Long teamId;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "hackathon_id", nullable = false)
	private Hackathon hackathon;

	@Column(length = TEAM_NAME_LENGTH, nullable = false)
	private String teamName;

	@Enumerated(STRING)
	@Column(nullable = false)
	private TeamStatus teamStatus;

	@Column(nullable = false)
	private Integer maxTeamMember;

	@Column(length = TEAM_DESC_LENGTH)
	private String teamDesc;

	@Builder
	public Team(Long teamId, Hackathon hackathon, String teamName, TeamStatus teamStatus, Integer maxTeamMember,
		String teamDesc) {
		this.teamId = teamId;
		this.hackathon = hackathon;
		this.teamName = teamName;
		this.teamStatus = teamStatus;
		this.maxTeamMember = maxTeamMember;
		this.teamDesc = teamDesc;
	}
}
