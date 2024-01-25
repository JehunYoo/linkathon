package com.link.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.link.back.entity.Project;

import feign.Param;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	@Query("delete from Project p where p.team.teamId = :teamId")
	void deleteByTeamId(@Param Long teamId);
}
