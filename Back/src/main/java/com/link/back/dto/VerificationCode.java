package com.link.back.dto;

import java.util.concurrent.TimeUnit;

import lombok.Getter;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@RedisHash(value = "refreshToken")
public class VerificationCode {

	@Id
	private String verificationCode;
	@TimeToLive
	private final long expireTime = TimeUnit.MINUTES.toMillis(3);;

	public VerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
}
