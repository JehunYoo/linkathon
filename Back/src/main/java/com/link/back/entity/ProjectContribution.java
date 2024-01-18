package com.link.back.entity;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class ProjectContribution {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long projectContributionId;

	@Column(nullable = false)
	private String author;

	@Column(nullable = false)
	private Integer row; // 총 입력 줄 수

	@Column(nullable = false)
	private Double ratioInComments; // 리뷰 작성 비율

	@Column(nullable = false)
	private Integer commits;

	@Column(nullable = false)
	private Integer insertions;

	@Column(nullable = false)
	private Integer deletions;

	@Column(nullable = false)
	private Double rationInChanges; // 변화 비율

	@ManyToOne
	@JoinColumn(name = "project_id")
	private  Project project;
}
