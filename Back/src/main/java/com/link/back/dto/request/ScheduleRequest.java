package com.link.back.dto.request;

import java.time.LocalTime;
import java.util.List;

import com.link.back.dto.ScheduleBaseDto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ScheduleRequest extends ScheduleBaseDto {
	@Builder
	public ScheduleRequest(@NotNull Long userId, @NotNull List<LocalTime> times) {
		super(userId, times);
	}
}
