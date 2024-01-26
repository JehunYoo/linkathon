package com.link.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.link.back.entity.Schedule;
import com.link.back.entity.User;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	List<Schedule> findByUser(User user);
	void deleteByUser(User user);
}
