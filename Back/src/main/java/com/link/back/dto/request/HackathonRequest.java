package com.link.back.dto.request;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public record HackathonRequest(
	String hackathonName,
	String hackathonTopic,
	LocalDate teamDeadlineDate,
	LocalDate startDate,
	LocalDate endDate,
	int maxPoint,
	int maxTeamMember
) {
}
