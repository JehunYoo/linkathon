package com.link.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.link.back.entity.Project;

public interface ProjectImageRepository extends JpaRepository<Project, Long> {
}
