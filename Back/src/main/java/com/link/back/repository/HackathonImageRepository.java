package com.link.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.link.back.entity.HackathonImage;

public interface HackathonImageRepository extends JpaRepository<HackathonImage,Long> {

}
