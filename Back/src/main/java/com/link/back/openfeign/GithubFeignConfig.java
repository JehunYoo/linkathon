package com.link.back.openfeign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import feign.RequestInterceptor;

public class GithubFeignConfig {
	@Value("${GIT_PERSONAL_TOKEN}")
	private String gitAuthorization;
	@Bean
	public RequestInterceptor requestInterceptor() {
		return requestTemplate -> {
			requestTemplate.header("Authorization", gitAuthorization);
			requestTemplate.header("Accept", "application/json");
			System.out.println(gitAuthorization);
		};

	}
}
