package com.link.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.link.back.entity.Hackathon;

public interface HackathonRepository extends JpaRepository<Hackathon, Long> {
}
