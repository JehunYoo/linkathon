package com.link.back.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
public record ScheduleRequest(@NotNull List<Integer> times) {
}
