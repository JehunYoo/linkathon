package com.link.back.dto.response;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReservationResponse {
	@NotNull
	private Long reservationId;
	private Long userId;
	private Boolean isLeader;
	private LocalDateTime reservationDatetime;

	@Builder
	public ReservationResponse(Long reservationId, Long userId, Boolean isLeader, LocalDateTime reservationDatetime) {
		this.reservationId = reservationId;
		this.userId = userId;
		this.isLeader = isLeader;
		this.reservationDatetime = reservationDatetime;
	}
}
