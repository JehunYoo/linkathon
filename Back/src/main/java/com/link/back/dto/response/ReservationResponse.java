package com.link.back.dto.response;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
public record ReservationResponse(@NotNull Long reservationId, @NotNull Long userId, @NotNull Boolean isLeader,
								  @NotNull LocalDateTime reservationDatetime) {
}
