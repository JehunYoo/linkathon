package com.link.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.link.back.entity.BackPerformanceMessage;

public interface BackPerformanceMessageRepository extends JpaRepository<BackPerformanceMessage,Long> {
}
