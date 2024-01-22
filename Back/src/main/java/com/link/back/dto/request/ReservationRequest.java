package com.link.back.dto.request;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReservationRequest {
	private final Long userId;
	private final LocalDateTime availableTime;

	@Builder
	public ReservationRequest(Long userId, LocalDateTime availableTime) {
		this.userId = userId;
		this.availableTime = availableTime;
	}
}
