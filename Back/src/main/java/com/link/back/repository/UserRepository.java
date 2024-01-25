package com.link.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.link.back.entity.User;

import feign.Param;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

	@Query("select user from User user"
		+ " join fetch UserSkill us"
		+ " join fetch Skill s"
		+ " join fetch UserImage ui"
		+ " where user.userId = :userId")
	User findUserInfoById(@Param("userId") Long userId);
}
