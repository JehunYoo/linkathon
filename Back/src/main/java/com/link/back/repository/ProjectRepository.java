package com.link.back.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.link.back.entity.Project;
import com.link.back.entity.ProjectStatus;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	public Page<Project> findAllByProjectStatusOrderByRegisteredDateDesc(ProjectStatus projectStatus,
		org.springframework.data.domain.Pageable pageable);

	public Page<Project> findByProjectStatusOrderByLikesDesc(ProjectStatus projectStatus, Pageable pageable);
}
