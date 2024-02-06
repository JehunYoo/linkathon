package com.link.back.repository;

import static com.link.back.entity.QSkill.*;
import static com.link.back.entity.QUser.*;
import static com.link.back.entity.QUserSkill.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.link.back.dto.request.UserSearchConditionDto;
import com.link.back.entity.Field;
import com.link.back.entity.QSkill;
import com.link.back.entity.QUserSkill;
import com.link.back.entity.User;
import com.link.back.entity.UserSkill;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class UserRepositoryImpl implements UserRepositoryCustom {

	@Autowired
	private JPAQueryFactory queryFactory;

	@Override
	public Page<User> findBySearchCondition(Pageable pageable, UserSearchConditionDto userSearchConditionDto) {

		List<User> userList = queryFactory.select(user)
			.from(user)
			.leftJoin(user.userSkills, userSkill).fetchJoin()
			.leftJoin(skill).on(skill.skillId.eq(userSkill.skill.skillId))
			.where(
				eqSkillSets(userSearchConditionDto.getSkillIds()),
				eqTier(userSearchConditionDto.getTier()),
				eqCareer(userSearchConditionDto.getCareer()),
				eqGender(userSearchConditionDto.getGender()),
				eqField(userSearchConditionDto.getField()))
			.fetch();

		return new PageImpl<>(userList, pageable, userList.size());
	}

	private BooleanExpression eqSkillSets(List<Long> skillIds) {
		if (skillIds == null || skillIds.isEmpty()) {
			return null;
		}
		return skillIds.stream()
			.map(skillId -> user.userSkills.any().skill.skillId.eq(skillId))
			.reduce(BooleanExpression::and)
			.orElse(null);
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
