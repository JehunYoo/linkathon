package com.link.back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.link.back.entity.BackPerformance;
import com.link.back.entity.Project;

public interface BackPerformanceRepository extends JpaRepository<BackPerformance,Long> {
	// Optional<Page<BackPerformance>> findByProjectId(Long projectId, Pageable pageable);
	Page<BackPerformance> findByProject(Project project, Pageable pageable);
}
