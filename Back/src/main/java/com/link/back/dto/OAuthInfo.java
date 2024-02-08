package com.link.back.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class OAuthInfo {
	@JsonProperty("access_token")
	private String accessToken;
}