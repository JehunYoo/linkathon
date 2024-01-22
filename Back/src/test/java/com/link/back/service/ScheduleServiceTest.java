package com.link.back.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

import com.link.back.dto.request.ScheduleRequest;
import com.link.back.dto.response.ScheduleResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.link.back.entity.Schedule;
import com.link.back.entity.ScheduleTime;
import com.link.back.entity.User;
import com.link.back.repository.ScheduleRepository;
import com.link.back.repository.UserRepository;

@SpringBootTest
@Transactional
class ScheduleServiceTest {

	private final UserRepository userRepository;
	private final ScheduleRepository scheduleRepository;
	private final ScheduleService scheduleService;

	@Autowired
	public ScheduleServiceTest(UserRepository userRepository, ScheduleRepository scheduleRepository,
		ScheduleService scheduleService) {
		this.userRepository = userRepository;
		this.scheduleRepository = scheduleRepository;
		this.scheduleService = scheduleService;
	}

	@BeforeEach
	void setup() {

	}

	@Test
	@DisplayName("Schedule service getScheduleByUserId")
	void getMySchedule() {
		// given
		User user = User.builder()
				.email("asd")
				.password("asd")
				.phoneNumber("asd")
				.name("asd")
				.gender(true)
				.birth(LocalDate.now())
				.rating(2)
				.registered(true)
				.introduce("asd")
				.joinState(false)
				.build();
		userRepository.save(user);

		Schedule schedule1 = Schedule.builder()
			.availableTime(ScheduleTime.HOUR_00)
			.user(user)
			.build();
		Schedule schedule2 = Schedule.builder()
			.availableTime(ScheduleTime.HOUR_01)
			.user(user)
			.build();
		scheduleRepository.saveAll(List.of(schedule1, schedule2));

		// when
		ScheduleResponse scheduleResponse = scheduleService.getScheduleByUserId(user.getUserId());

		// then
		List<LocalTime> times = scheduleResponse.getTimes();
		System.out.println(scheduleResponse.getTimes());
		assertEquals(schedule1.getAvailableTime().toLocalTime(), times.get(0));
		assertEquals(schedule2.getAvailableTime().toLocalTime(), times.get(1));

	}

	@Test
	@DisplayName("Schedule service getScheduleByUserId")
	void updateMySchedule() {
		// given
		User user = User.builder()
			.email("asd")
			.password("asd")
			.phoneNumber("asd")
			.name("asd")
			.gender(true)
			.birth(LocalDate.now())
			.rating(2)
			.registered(true)
			.introduce("asd")
			.joinState(false)
			.build();
		userRepository.save(user);

		Schedule schedule1 = Schedule.builder()
			.availableTime(ScheduleTime.HOUR_00)
			.user(user)
			.build();
		Schedule schedule2 = Schedule.builder()
			.availableTime(ScheduleTime.HOUR_01)
			.user(user)
			.build();
		Schedule schedule3 = Schedule.builder()
			.availableTime(ScheduleTime.HOUR_02)
			.user(user)
			.build();
		scheduleRepository.saveAll(List.of(schedule1, schedule2)); // HOUR_00, HOUR_01

		ScheduleRequest scheduleRequest = ScheduleRequest.builder() // HOUR_01, HOUR_02
			.userId(user.getUserId())
			.times(Stream.of(schedule2, schedule3)
				.map((schedule) -> schedule.getAvailableTime().toLocalTime())
				.toList())
			.build();

		// when
		scheduleService.updateMySchedule(scheduleRequest);

		// then
		List<Schedule> schedules = scheduleRepository.findByUser(user);
		assertEquals(schedules.get(0).getAvailableTime(), schedule2.getAvailableTime());
		assertEquals(schedules.get(1).getAvailableTime(), schedule3.getAvailableTime());

	}
}
