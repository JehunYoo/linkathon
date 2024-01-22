package com.link.back.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.link.back.dto.request.ScheduleRequest;
import com.link.back.dto.response.ScheduleResponse;
import com.link.back.service.ScheduleService;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {
	private ScheduleService scheduleService;

	@GetMapping
	public ResponseEntity<ScheduleResponse> getMySchedule() {
		Long userId = 1234L; // 테스트용, 토큰 헤더에서 자신의 userId 가져올 예정
		ScheduleResponse scheduleResponse = scheduleService.getScheduleByUserId(userId);
		return new ResponseEntity<>(scheduleResponse, HttpStatus.OK);
	}

	@GetMapping("/{user_id}")
	public ResponseEntity<ScheduleResponse> getUserSchedule(@PathVariable("user_id") Long userId) {
		ScheduleResponse scheduleResponse = scheduleService.getScheduleByUserId(userId);
		return new ResponseEntity<>(scheduleResponse, HttpStatus.OK);
	}

	@PutMapping
	public ScheduleResponse updateMySchedule(@NotNull ScheduleRequest scheduleRequest) {
		return scheduleService.updateMySchedule(scheduleRequest);
	}

}
