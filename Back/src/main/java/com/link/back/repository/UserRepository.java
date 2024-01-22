package com.link.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.link.back.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
