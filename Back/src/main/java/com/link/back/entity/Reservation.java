package com.link.back.entity;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Reservation {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long reservationId;

	@Column(nullable = false)
	private LocalDateTime reservationDatetime;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "leader_id", nullable = false)
	private User leader;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private User member;

	@Builder
	public Reservation(Long reservationId, LocalDateTime reservationDatetime, User leader, User member) {
		this.reservationId = reservationId;
		this.reservationDatetime = reservationDatetime;
		this.leader = leader;
		this.member = member;
	}

	public void update(LocalDateTime reservationDatetime) {
		this.reservationDatetime = reservationDatetime;
	}

	public Boolean isLeader(Long userId) {
		return leader.getUserId().equals(userId);
	}

	public Boolean isMember(Long userId) {
		return member.getUserId().equals(userId);
	}

	public Boolean isMyReservation(Long userId) {
		return isMember(userId) || isLeader(userId);
	}
}