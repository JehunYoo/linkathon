package com.link.back.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class OauthToken {
	private String accesToken;
	private String tokenType;
	private String scope;
	private String bearer;

	@JsonProperty("access_token")
	public void setAccesToken(String accesToken) {
		this.accesToken = accesToken;
	}

	@JsonProperty("token_type")
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getAccesToken() {
		return accesToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public String getScope() {
		return scope;
	}

	public String getBearer() {
		return bearer;
	}
}