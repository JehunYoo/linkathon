package com.link.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.link.back.entity.LighthouseReport;

public interface LightHouseReportRepository extends JpaRepository<LighthouseReport, Long> {
	void removeAllByProject_ProjectId(Long projectId);

	List<LighthouseReport> getAllByProject_ProjectId(Long projectId);
}
