package com.link.back.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.link.back.dto.response.HackathonsResponseDto;
import com.link.back.entity.Hackathon;

import com.link.back.entity.QHackathon;
import com.link.back.exception.NotParameterException;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class HackathonsRepository {
	private final JPAQueryFactory queryFactory;

	// public HackathonQuery(EntityManager entityManager) {
	// 	this.queryFactory = new JPAQueryFactory(entityManager);
	// }

	public Page<Hackathon> getAllHackathons(Pageable pageable, String status) {
		LocalDate currentDate = LocalDate.now();
		System.out.println(currentDate);
		BooleanBuilder whereConditions = new BooleanBuilder();
		if ("모집중".equals(status)) {
			whereConditions.and(registrationDateBetweenAndTeamRegistration(currentDate));
		} else if ("진행중".equals(status)) {
			whereConditions.and(hackathonStartDateBetweenAndEndDate(currentDate));
		} else if ("완료됨".equals(status)) {
			whereConditions.and(hackathonEndDateGreaterThan(currentDate));
		} else {
			throw new NotParameterException();
		}

		List<Hackathon> hackathons= queryFactory
			.selectFrom(QHackathon.hackathon)
			.where(whereConditions)
			.offset(pageable.getPageNumber())   // (2) 페이지 번호
			.limit(pageable.getPageSize())  // (3) 페이지 사이즈
			.fetch();

		int count = hackathons.size();

		return new PageImpl<>(hackathons,pageable,count);
	}

	private BooleanExpression registrationDateBetweenAndTeamRegistration(LocalDate currentDate) {

		return QHackathon.hackathon.registerDate.loe(currentDate)
			.and(QHackathon.hackathon.teamDeadlineDate.goe(currentDate));
	}

	private BooleanExpression hackathonStartDateBetweenAndEndDate(LocalDate currentDate) {
		return QHackathon.hackathon.startDate.loe(currentDate)
			.and(QHackathon.hackathon.endDate.goe(currentDate));
	}

	private BooleanExpression hackathonEndDateGreaterThan(LocalDate currentDate) {
		return QHackathon.hackathon.endDate.lt(currentDate);
	}
}
