package com.link.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.link.back.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
