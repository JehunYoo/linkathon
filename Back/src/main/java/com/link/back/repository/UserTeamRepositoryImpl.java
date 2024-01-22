package com.link.back.repository;

import static com.link.back.entity.MemberStatus.*;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.link.back.entity.QTeam;
import com.link.back.entity.QUser;
import com.link.back.entity.QUserTeam;
import com.link.back.entity.UserTeam;

public class UserTeamRepositoryImpl extends QuerydslRepositorySupport implements UserTeamQueryDslRepository {

	public UserTeamRepositoryImpl(Class<?> domainClass) {
		super(domainClass);
	}

	@Override
	public List<UserTeam> findSuggestedCandidates() {
		// QUserTeam userTeam = QUserTeam.userTeam;
		// QTeam team = QTeam.team;
		// QUser user = QUser.user;
		//
		//
		// from(userTeam)
		// 	.fetchJoin()
		// 	.where(userTeam.team.eq(team).and(userTeam.memberStatus.eq(SUGGESTED)))

		return null;
	}
}
