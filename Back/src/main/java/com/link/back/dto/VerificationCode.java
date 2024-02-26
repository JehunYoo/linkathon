package com.link.back.dto;

import java.util.concurrent.TimeUnit;

import lombok.Getter;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@RedisHash(value = "verificationCode", timeToLive = 3 * 60)
public class VerificationCode {

	@Id
	private String verificationCode;
	private final String email;

	public VerificationCode(String verificationCode, String email) {

		this.verificationCode = verificationCode;
		this.email = email;

	}
}
