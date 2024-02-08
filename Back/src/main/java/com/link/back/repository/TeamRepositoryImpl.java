package com.link.back.repository;

import static com.link.back.entity.QTeam.*;
import static com.link.back.entity.QTeamSkill.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.link.back.dto.request.TeamSearchConditionDto;
import com.link.back.entity.Team;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TeamRepositoryImpl implements TeamRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Page<Team> findTeamByHackathonAndTeamSearchCond(Long hackathonId,
		TeamSearchConditionDto teamSearchConditionDto, Pageable pageable) {

		List<Team> teamList;
		JPQLQuery<Team> countQuery;

		if (teamSearchConditionDto != null) {
			teamList = queryFactory.selectFrom(team)
				.where(
					eqSkillSets(teamSearchConditionDto.getSkillIds()),
					eqHackathonId(hackathonId))
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();

			countQuery = queryFactory.selectFrom(team)
				.where(
					eqSkillSets(teamSearchConditionDto.getSkillIds()),
					eqHackathonId(hackathonId));
		} else {
			teamList = queryFactory.selectFrom(team)
				.where(
					eqHackathonId(hackathonId))
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();

			countQuery = queryFactory.selectFrom(team)
				.where(eqHackathonId(hackathonId));
		}
		return new PageImpl<>(teamList, pageable, countQuery.fetchCount());
	}

	private BooleanExpression eqHackathonId(Long hackathonId) {
		return (hackathonId == null) ? null : team.hackathon.hackathonId.eq(hackathonId);
	}

	private BooleanExpression eqSkillSets(List<Long> skillIds) {
		if (skillIds == null || skillIds.isEmpty()) {
			return null;
		}
		JPAQuery<Team> skillSubQuery = queryFactory.select(team)
			.from(team)
			.innerJoin(team.teamSkills, teamSkill)
			.where(teamSkill.skill.skillId.in(skillIds))
			.groupBy(team.teamId)
			.having(teamSkill.skill.skillId.count().eq((long)skillIds.size()));

		return team.in(skillSubQuery);
	}
}
