package com.link.back.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.link.back.dto.request.ScheduleRequest;
import com.link.back.dto.response.ScheduleResponse;
import com.link.back.entity.Schedule;
import com.link.back.entity.ScheduleTime;
import com.link.back.entity.User;
import com.link.back.repository.ScheduleRepository;
import com.link.back.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleService {
	private final ScheduleRepository scheduleRepository;
	private final UserRepository userRepository;

	public ScheduleResponse getScheduleByUserId(Long userId) {
		User user = userRepository.getReferenceById(userId);
		List<Schedule> schedules = scheduleRepository.findByUser(user);
		return ScheduleResponse.toScheduleResponse(userId, schedules);
	}

	@Transactional
	public ScheduleResponse updateMySchedule(ScheduleRequest scheduleRequest) {

		User user = userRepository.getReferenceById(scheduleRequest.getUserId());

		scheduleRepository.deleteByUser(user);

		List<Schedule> schedules = scheduleRequest
			.getTimes()
			.stream()
			.map(
				time -> Schedule.builder()
					.user(user)
					.availableTime(ScheduleTime.values()[time.getHour()])
					.build()
			)
			.toList();

		scheduleRepository.saveAll(schedules);
		return null;
	}

}
