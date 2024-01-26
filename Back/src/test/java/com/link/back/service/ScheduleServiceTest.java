// package com.link.back.service;
//
// import static org.junit.jupiter.api.Assertions.*;
//
// import java.time.LocalDate;
// import java.time.LocalTime;
// import java.util.List;
// import java.util.stream.Stream;
//
// import com.link.back.dto.request.ScheduleRequest;
// import com.link.back.dto.response.ScheduleResponse;
//
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.transaction.annotation.Transactional;
//
// import com.link.back.entity.Schedule;
// import com.link.back.entity.User;
// import com.link.back.repository.ScheduleRepository;
// import com.link.back.repository.UserRepository;
//
// @SpringBootTest
// @Transactional
// class ScheduleServiceTest {
//
// 	private final UserRepository userRepository;
// 	private final ScheduleRepository scheduleRepository;
// 	private final ScheduleService scheduleService;
//
// 	@Autowired
// 	public ScheduleServiceTest(UserRepository userRepository, ScheduleRepository scheduleRepository,
// 		ScheduleService scheduleService) {
// 		this.userRepository = userRepository;
// 		this.scheduleRepository = scheduleRepository;
// 		this.scheduleService = scheduleService;
// 	}
//
// 	@BeforeEach
// 	void setup() {
//
// 	}
//
// 	// @Test
// 	// @DisplayName("Schedule service getScheduleByUserId")
// 	// void getMySchedule() {
// 	// 	// given
// 	// 	User user = User.builder()
// 	// 			.email("asd")
// 	// 			.password("asd")
// 	// 			.phoneNumber("asd")
// 	// 			.name("asd")
// 	// 			.gender(true)
// 	// 			.birth(LocalDate.now())
// 	// 			.rating(2)
// 	// 			.registered(true)
// 	// 			.introduce("asd")
// 	// 			.joinState(false)
// 	// 			.build();
// 	// 	userRepository.save(user);
// 	//
// 	// 	Schedule schedule1 = Schedule.builder()
// 	// 		.availableTime(LocalTime.of(0, 0))
// 	// 		.user(user)
// 	// 		.build();
// 	// 	Schedule schedule2 = Schedule.builder()
// 	// 		.availableTime(LocalTime.of(1, 0))
// 	// 		.user(user)
// 	// 		.build();
// 	// 	scheduleRepository.saveAll(List.of(schedule1, schedule2));
// 	//
// 	// 	// when
// 	// 	ScheduleResponse scheduleResponse = scheduleService.getScheduleByUserId(user.getUserId());
// 	//
// 	// 	// then
// 	// 	List<Integer> times = scheduleResponse.times();
// 	// 	System.out.println(scheduleResponse.times());
// 	// 	assertEquals(schedule1.getAvailableTime().getHour(), times.get(0));
// 	// 	assertEquals(schedule2.getAvailableTime().getHour(), times.get(1));
// 	//
// 	// }
//
// 	@Test
// 	@DisplayName("Schedule service getScheduleByUserId")
// 	void updateMySchedule() {
// 		// given
// 		User user = User.builder()
// 			.email("asd")
// 			.password("asd")
// 			.phoneNumber("asd")
// 			.name("asd")
// 			.gender(true)
// 			.birth(LocalDate.now())
// 			.rating(2)
// 			.registered(true)
// 			.introduce("asd")
// 			.joinState(false)
// 			.build();
// 		userRepository.save(user);
//
// 		Schedule schedule1 = Schedule.builder()
// 			.availableTime(LocalTime.of(0, 0))
// 			.user(user)
// 			.build();
// 		Schedule schedule2 = Schedule.builder()
// 			.availableTime(LocalTime.of(0, 0))
// 			.user(user)
// 			.build();
// 		Schedule schedule3 = Schedule.builder()
// 			.availableTime(LocalTime.of(0, 0))
// 			.user(user)
// 			.build();
// 		scheduleRepository.saveAll(List.of(schedule1, schedule2)); // HOUR_00, HOUR_01
//
// 		ScheduleRequest scheduleRequest = ScheduleRequest.builder() // HOUR_01, HOUR_02
// 			.times(Stream.of(schedule2, schedule3)
// 				.map((schedule) -> schedule.getAvailableTime().getHour())
// 				.toList())
// 			.build();
//
// 		// when
// 		scheduleService.updateMySchedule(user.getUserId(), scheduleRequest);
//
// 		// then
// 		List<Schedule> schedules = scheduleRepository.findByUser(user);
// 		assertEquals(schedules.get(0).getAvailableTime(), schedule2.getAvailableTime());
// 		assertEquals(schedules.get(1).getAvailableTime(), schedule3.getAvailableTime());
//
// 	}
// }
