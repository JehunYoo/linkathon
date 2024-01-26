package com.link.back.entity;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@ToString
public class BackPerformance {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long backPerformanceId;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;

	private Integer bugs;

	private Integer codeSmells;

	private Double coverage;

	private Double duplications;

	private Double securityRating;

	private Double vulnerabilities;

	@Builder
	public BackPerformance(Project project, Integer bugs, Integer codeSmells, Double coverage, Double duplications,
		Double securityRating,
		Double vulnerabilities) {
		this.project = project;
		this.bugs = bugs;
		this.codeSmells = codeSmells;
		this.coverage = coverage;
		this.duplications = duplications;
		this.securityRating = securityRating;
		this.vulnerabilities = vulnerabilities;
	}
	// @Column(length = 300)
	// private String fileName;

	// @Column(nullable = false, length = 20)
	// private String severity;
	//
	// private Integer line;
	//
	// private String message;
	//
	// @Column(nullable = false)
	// private String type;
}
