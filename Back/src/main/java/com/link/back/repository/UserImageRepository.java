package com.link.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.link.back.entity.UserImage;

public interface UserImageRepository extends JpaRepository<UserImage, Long> {
}
