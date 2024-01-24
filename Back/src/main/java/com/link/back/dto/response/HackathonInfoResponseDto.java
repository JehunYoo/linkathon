package com.link.back.dto.response;

import java.time.LocalDate;

import com.link.back.entity.Hackathon;

import lombok.Getter;

@Getter
public class HackathonInfoResponseDto {

	private final String hackathonName;
	private final LocalDate registerDate;
	private final LocalDate teamDeadlineDate;
	private final LocalDate startDate;
	private final LocalDate endDate;
	private final String hackathonImageName;
	private final String hackathonOriginImageName;

	public HackathonInfoResponseDto(Hackathon hackathon) {
		this.hackathonName = hackathon.getHackathonName();
		this.registerDate = hackathon.getRegisterDate();
		this.teamDeadlineDate = hackathon.getTeamDeadlineDate();
		this.startDate = hackathon.getStartDate();
		this.endDate = hackathon.getEndDate();
		this.hackathonImageName = hackathon.getHackathonImage().getHackathonImageName();
		this.hackathonOriginImageName = hackathon.getHackathonImage().getHackathonOriginImageName();
	}
}
