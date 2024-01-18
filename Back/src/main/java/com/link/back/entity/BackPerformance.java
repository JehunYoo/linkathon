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
public class BackPerformance {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long backPerformanceId;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;

	@Column(length = 300)
	private String fileName;

	@Column(nullable = false, length = 20)
	private String severity;

	private Integer line;

	private String message;

	@Column(nullable = false)
	private String type;
}
