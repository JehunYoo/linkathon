package com.link.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.link.back.entity.User;
import com.link.back.entity.UserSkill;

public interface UserSkillRepository extends JpaRepository<UserSkill, Long> {

	void deleteByUser(User user);

}
