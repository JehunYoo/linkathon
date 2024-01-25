package com.link.back.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.link.back.dto.request.ReservationRequest;
import com.link.back.dto.response.ReservationResponse;
import com.link.back.service.ReservationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {

	private final ReservationService reservationService;

	@GetMapping
	public List<ReservationResponse> getReservation() {
		Long myUserId = 1234L;
		return reservationService.getMyReservations(myUserId);
	}

	@PostMapping
	public void postReservation(ReservationRequest reservationRequest) {
		Long myUserId = 1234L;
		reservationService.createMyReservation(myUserId, reservationRequest);
	}

	@PutMapping("/{reservation_id}")
	public void putReservation(@PathVariable("reservation_id") Long reservationId, ReservationRequest reservationRequest) {
		Long myUserId = 1234L;
		reservationService.updateMyReservation(reservationId, myUserId, reservationRequest);
	}

	@DeleteMapping("/{reservation_id}")
	public void deleteReservation(@PathVariable("reservation_id") Long reservationId) {
		Long myUserId = 1234L;
		reservationService.deleteMyReservation(myUserId, reservationId);
	}

}
