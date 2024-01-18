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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class FrontPerformance {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long frontPerformanceId;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;

	@Column(nullable = false)
	private Integer performance;

	@Column(nullable = false)
	private Integer accessibility;

	@Column(nullable = false)
	private Integer bestPractices;

	@Column(nullable = false)
	private Integer seo;

	@Column(nullable = false)
	private Integer pwa;

}
