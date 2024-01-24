package com.link.back.entity;

import static com.link.back.config.AppConstant.*;
import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;
import static lombok.AccessLevel.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.util.Lazy;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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

	@OneToMany(mappedBy = "team", cascade = REMOVE)
	private List<UserTeam> userTeamList = new ArrayList<>();

	@Column(length = TEAM_NAME_LENGTH, nullable = false)
	private String teamName;

	@Enumerated(STRING)
	@Column(nullable = false)
	private TeamStatus teamStatus;

	@Column(nullable = false)
	private Integer teamMember;

	@Column(length = TEAM_DESC_LENGTH)
	private String teamDesc;

	@OneToMany(mappedBy = "team")
	List<TeamSkill> teamSkills = new ArrayList<>();

	@OneToOne(fetch = LAZY)
	@JoinColumn(name = "team_image_id")
	private TeamImage teamImage;

	@Builder
	public Team(String teamName, TeamStatus teamStatus, Integer teamMember, String teamDesc) {
		this.teamName = teamName;
		this.teamStatus = teamStatus;
		this.teamMember = teamMember;
		this.teamDesc = teamDesc;
	}
}
