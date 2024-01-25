package com.link.back.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.link.back.entity.Hackathon;

public interface HackathonRepository extends JpaRepository<Hackathon,Long> {

}
