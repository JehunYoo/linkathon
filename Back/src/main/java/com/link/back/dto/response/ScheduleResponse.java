package com.link.back.dto.response;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ScheduleResponse(@NotNull Long userId, @NotNull List<Integer> times) {
}
