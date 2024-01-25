package com.link.back.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.link.back.entity.Project;
import com.link.back.entity.ProjectStatus;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	public Page<Project> findAllByProjectStatusOrderByRegisteredDateDesc(ProjectStatus projectStatus,
		org.springframework.data.domain.Pageable pageable);

	public Page<Project> findByProjectStatus(ProjectStatus projectStatus, Pageable pageable);

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
}
