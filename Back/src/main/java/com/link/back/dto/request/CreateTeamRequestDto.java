package com.link.back.dto.request;

import java.util.List;

import lombok.Getter;

@Getter
public class CreateTeamRequestDto {

	private String teamName;
	private String teamDesc;
	private List<Long> teamSkills;

}
