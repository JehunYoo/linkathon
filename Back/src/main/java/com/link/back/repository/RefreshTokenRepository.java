package com.link.back.repository;

import com.link.back.dto.RefreshToken;

import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

}