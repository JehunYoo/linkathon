package com.link.back.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.link.back.dto.response.SkillTypeResponseDto;
import com.link.back.repository.SkillRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SkillService {

	private final SkillRepository skillRepository;

	public Map<String, List<SkillTypeResponseDto>> getSkillsGroupByType() {
		return skillRepository.findAll().stream()
			.collect(Collectors.groupingBy(skill -> skill.getSkillType().getName(),
				Collectors.mapping(SkillTypeResponseDto::new, Collectors.toList())));
	}

}
