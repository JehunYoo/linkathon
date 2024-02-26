package com.link.back.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ReservationRequest(
	@NotNull Long userId,
	@NotNull LocalDateTime reservationDateTime) {
}
