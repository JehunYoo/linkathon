package com.link.back.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.link.back.dto.request.TeamSearchConditionDto;
import com.link.back.entity.Team;

public interface TeamRepositoryCustom {

	Page<Team> findTeamByHackathonAndTeamSearchCond(Long hackathonId, TeamSearchConditionDto teamSearchConditionDto, Pageable pageable);

}
