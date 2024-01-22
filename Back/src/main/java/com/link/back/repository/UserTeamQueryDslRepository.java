package com.link.back.repository;

import java.util.List;

import com.link.back.entity.UserTeam;

public interface UserTeamQueryDslRepository {

	List<UserTeam> findSuggestedCandidates();
}
