package com.link.back.dto;

import java.time.LocalTime;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleBaseDto {
	@NotNull
	private Long userId;
	@NotNull
	private List<LocalTime> times;
}
