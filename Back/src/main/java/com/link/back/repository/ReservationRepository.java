package com.link.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.link.back.entity.Reservation;
import com.link.back.entity.User;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	List<Reservation> findByLeaderOrMember(User leader, User member);
}
