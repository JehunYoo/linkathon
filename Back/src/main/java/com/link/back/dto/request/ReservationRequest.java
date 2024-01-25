package com.link.back.dto.request;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Builder
public record ReservationRequest(Long userId, LocalDateTime availableTime) {
}
