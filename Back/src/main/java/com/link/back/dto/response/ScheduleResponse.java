package com.link.back.dto.response;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import com.link.back.dto.ScheduleBaseDto;
import com.link.back.entity.Schedule;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ScheduleResponse extends ScheduleBaseDto {

	@Builder
	public ScheduleResponse(@NotNull Long userId, @NotNull List<LocalTime> times) {
		super(userId, times);
	}

	public static ScheduleResponse toScheduleResponse(Long userId, List<Schedule> schedules) {
		return new ScheduleResponseBuilder()
			.userId(userId)
			.times(schedules.stream().map(
				schedule -> schedule.getAvailableTime().toLocalTime()
			).collect(Collectors.toList()))
			.build();
	}
}
