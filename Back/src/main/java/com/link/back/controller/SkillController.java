package com.link.back.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.link.back.dto.response.SkillTypeResponseDto;
import com.link.back.service.SkillService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/skill")
public class SkillController {

	private final SkillService skillService;

	@GetMapping
	public Map<String, List<SkillTypeResponseDto>> findSkills() {
		return skillService.getSkillsGroupByType();
	}

}
