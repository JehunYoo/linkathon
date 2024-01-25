package com.link.back.repository;

import static com.link.back.entity.QUser.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.link.back.dto.request.UserSearchConditionDto;
import com.link.back.entity.Field;
import com.link.back.entity.QUserSkill;
import com.link.back.entity.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {

	@Autowired
	private JPAQueryFactory queryFactory;

	public UserRepositoryImpl() {
		super(User.class);
	}

	@Override
	public Page<User> findBySearchCondition(Pageable pageable, UserSearchConditionDto userSearchConditionDto) {
		JPQLQuery<User> query = null;
		if(userSearchConditionDto == null) {
			query = queryFactory.selectFrom(user);
		} else {
			query = queryFactory.selectFrom(user)
				.where(eqSkillSets(userSearchConditionDto.getSkillIds()),
					eqTier(userSearchConditionDto.getTier()),
					eqCareer(userSearchConditionDto.getCareer()),
					eqGender(userSearchConditionDto.getGender()),
					eqField(userSearchConditionDto.getField()));
		}
		List<User> users = this.getQuerydsl().applyPagination(pageable, query).fetch();
		return new PageImpl<>(users, pageable, query.fetchCount());
	}

	private BooleanExpression eqSkillSets(List<Long> skillIds) {
		if (skillIds == null || skillIds.isEmpty()) {
			return null;
		}
		QUserSkill userSkill = QUserSkill.userSkill;
		BooleanExpression predicate = null;
		for (Long skillId : skillIds) {
			BooleanExpression skillCondition = userSkill.skill.skillId.eq(skillId);
			if (predicate == null) {
				predicate = skillCondition;
			} else {
				predicate = predicate.and(skillCondition);
			}
		}
		return predicate;
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

	private BooleanExpression eqField(Field field) {
		return (field == null) ? null : user.field.eq(field);
	}

}
