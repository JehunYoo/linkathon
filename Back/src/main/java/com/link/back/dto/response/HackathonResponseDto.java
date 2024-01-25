package com.link.back.dto.response;


import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;



@Getter
public class HackathonResponseDto {

	private final String hackathonName;
	private final LocalDate registerDate;
	private final LocalDate teamDeadlineDate;
	private final LocalDate startDate;
	private final LocalDate endDate;
	private final Integer maxPoint;
	private final String hackathonImageName;
	private final String hackathonImageUrl;
	private final String hackathonOriginImageName;

	@Builder
	public HackathonResponseDto(String hackathonName, LocalDate registerDate, LocalDate teamDeadlineDate,
		LocalDate startDate, LocalDate endDate, Integer maxPoint, String hackathonImageName, String hackathonImageUrl,
		String hackathonOriginImageName) {
		this.hackathonName = hackathonName;
		this.registerDate = registerDate;
		this.teamDeadlineDate = teamDeadlineDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.maxPoint = maxPoint;
		this.hackathonImageName = hackathonImageName;
		this.hackathonImageUrl = hackathonImageUrl;
		this.hackathonOriginImageName = hackathonOriginImageName;
	}
}
