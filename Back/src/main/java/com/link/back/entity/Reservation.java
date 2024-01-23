package com.link.back.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Reservation {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long reservationId;

	@Column(nullable = false)
	private LocalDateTime reservationDateTime;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "leader_id", nullable = false)
	private User leader;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private User member;

	@Builder
	public Reservation(Long reservationId, LocalDateTime reservationDateTime, User leader, User member) {
		this.reservationId = reservationId;
		this.reservationDateTime = reservationDateTime;
		this.leader = leader;
		this.member = member;
	}

	public void update(LocalDateTime reservationDate) {
		this.reservationDateTime = reservationDate;
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