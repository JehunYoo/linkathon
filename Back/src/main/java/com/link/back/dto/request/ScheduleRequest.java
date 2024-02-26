package com.link.back.dto.request;

import java.time.LocalTime;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ScheduleRequest(@NotNull List<LocalTime> times) {
}
