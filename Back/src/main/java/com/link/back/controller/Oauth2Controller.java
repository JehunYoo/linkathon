package com.link.back.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/oauth2/authorization")
public class Oauth2Controller {

	@Value("${spring.security.oauth2.client.registration.google.client-id}")
	private String googleId;

	@Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
	private String googleUri;

	@Value("${spring.security.oauth2.client.registration.google.scope}")
	private String googleScope;

	@Value("${spring.security.oauth2.client.registration.github.client-id}")
	private String gihubId;

	@Value("${spring.security.oauth2.client.registration.github.client-secret}")
	private String githubSecret;

	@Value("${spring.security.oauth2.client.registration.github.scope}")
	private String githubScope;

	@CrossOrigin(origins = "http://localhost:5173")
	@GetMapping("/google")
	public String googleLogin(){

		String URL = "https://accounts.google.com/o/oauth2/v2/auth?response_type=code&client_id=" + googleId + "&redirect_uri=" + googleUri + "&scope=" + googleScope;

		return URL;
	}


	@GetMapping("/github")
	public String githubLogin(){

		String URL = "https://accounts.google.com/o/oauth2/v2/auth?response_type=code&client_id=" + googleId + "&redirect_uri=" + googleUri + "&scope=" + googleScope;

		return "redirect:" + URL;
	}

}
