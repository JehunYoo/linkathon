package com.link.back.dto.response;

import lombok.Builder;
import lombok.Getter;


@Getter
public class BackPerformanceResponseDto {

	private final Long backPerformanceId;

	private final Integer bugs;

	private final Integer codeSmells;

	private final Double coverage;

	private final Double duplications;

	private final Double securityRating;

	private final Double vulnerabilities;

	@Builder

	public BackPerformanceResponseDto(Long backPerformanceId, Integer bugs, Integer codeSmells, Double coverage, Double duplications,
		Double securityRating, Double vulnerabilities) {
		this.backPerformanceId = backPerformanceId;
		this.bugs = bugs;
		this.codeSmells = codeSmells;
		this.coverage = coverage;
		this.duplications = duplications;
		this.securityRating = securityRating;
		this.vulnerabilities = vulnerabilities;
	}
}
