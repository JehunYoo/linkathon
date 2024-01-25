package com.link.back.dto;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "refreshToken", timeToLive = 604800000L)
public class RefreshToken {

    @Id
    private Long id;
    private String refreshToken;

    public RefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
