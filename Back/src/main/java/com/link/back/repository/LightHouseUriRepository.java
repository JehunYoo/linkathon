package com.link.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.link.back.entity.LighthouseUri;

public interface LightHouseUriRepository extends JpaRepository<LighthouseUri, Long> {
	List<LighthouseUri> findByProject_ProjectId(Long projectId);
	void removeByProject_ProjectId(Long projectId);
}
