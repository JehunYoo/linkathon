package com.link.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.link.back.entity.Project;
import com.link.back.entity.ProjectLike;
import com.link.back.entity.User;

public interface ProjectLikeRepository extends JpaRepository<ProjectLike, Long> {
	Optional<ProjectLike> findByProjectAndUser(Project project, User user);

	int countByProject(Project project);
}
