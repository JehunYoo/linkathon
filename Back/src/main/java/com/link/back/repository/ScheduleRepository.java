package com.link.back.repository;

import java.util.List;
import java.util.Optional;

import com.link.back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.link.back.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	List<Schedule> findByUser(User user);
	void deleteByUser(User user);
}
