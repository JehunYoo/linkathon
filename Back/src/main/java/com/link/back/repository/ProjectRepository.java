package com.link.back.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.link.back.entity.Project;

import feign.Param;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	@Query("SELECT p"
		+ " FROM Project p JOIN p.team t JOIN UserTeam ut ON t = ut.team"
		+ " WHERE ut.user.userId = :userId")
	public List<Project> findByUserId(Long userId);

	@Query("SELECT p"
		+ " FROM Project p LEFT JOIN ProjectLike pl ON p = pl.project"
		+ " WHERE p.projectStatus = :projectStatus"
		+ " GROUP BY p"
		+ " ORDER BY COUNT(pl.project) DESC")
	public Page<Project> findByProjectStatusOrderByLikesDesc(ProjectStatus projectStatus, Pageable pageable);

	@Query("delete from Project p where p.team.teamId = :teamId")
	void deleteByTeamId(@Param Long teamId);

}