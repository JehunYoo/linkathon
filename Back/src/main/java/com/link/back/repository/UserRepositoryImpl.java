package com.link.back.repository;

import static com.link.back.entity.QUser.*;
import static com.link.back.entity.QUserSkill.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.link.back.dto.request.UserSearchConditionDto;
import com.link.back.entity.Field;
import com.link.back.entity.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Page<User> findBySearchCondition(Pageable pageable, UserSearchConditionDto userSearchConditionDto) {
		List<User> userList;
		JPQLQuery<User> countQuery;
		if (userSearchConditionDto != null) {
			userList = queryFactory.selectFrom(user)
				.where(
					eqSkillSets(userSearchConditionDto.getSkillIds()),
					eqTier(userSearchConditionDto.getTier()),
					eqCareer(userSearchConditionDto.getCareer()),
					eqGender(userSearchConditionDto.getGender()),
					eqField(userSearchConditionDto.getField()))
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();

			countQuery = queryFactory.selectFrom(user)
				.where(
					eqSkillSets(userSearchConditionDto.getSkillIds()),
					eqTier(userSearchConditionDto.getTier()),
					eqCareer(userSearchConditionDto.getCareer()),
					eqGender(userSearchConditionDto.getGender()),
					eqField(userSearchConditionDto.getField())
				);
		} else {
			userList = queryFactory.selectFrom(user)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();

			countQuery = queryFactory.selectFrom(user);
		}
		return new PageImpl<>(userList, pageable, countQuery.fetchCount());
	}

	private BooleanExpression eqSkillSets(List<Long> skillIds) {
		if (skillIds == null || skillIds.isEmpty()) {
			return null;
		}

		// user와 userSkill을 조인한 후, skillId가 주어진 skillIds를 모두 포함하는 경우를 선택하는 서브쿼리
		JPQLQuery<User> skillSubQuery = queryFactory.select(user)
			.from(user)
			.innerJoin(user.userSkills, userSkill)
			.where(userSkill.skill.skillId.in(skillIds))
			.groupBy(user.userId)
			.having(userSkill.skill.skillId.count().eq((long)skillIds.size()));

		return user.in(skillSubQuery);
	}

	private BooleanExpression eqTier(Integer rating) {
		return (rating == null) ? null : user.rating.between(rating, rating + 99);
	}

	private BooleanExpression eqCareer(Integer career) {
		return (career == null) ? null : user.career.eq(career);
	}

	private BooleanExpression eqGender(Boolean gender) {
		return (gender == null) ? null : user.gender.eq(gender);
	}

	private BooleanExpression eqField(String field) {
		return (field == null) ? null : user.field.eq(Field.valueOf(field));
	}

}
