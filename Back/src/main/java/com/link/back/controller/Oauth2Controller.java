package com.link.back.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.link.back.dto.GithubProfile;
import com.link.back.dto.JwtToken;
import com.link.back.dto.OAuthInfo;
import com.link.back.dto.RefreshToken;
import com.link.back.entity.User;
import com.link.back.repository.RefreshTokenRepository;
import com.link.back.repository.UserRepository;
import com.link.back.security.JwtTokenProvider;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class Oauth2Controller {

	@Value("${spring.security.oauth2.client.registration.github.client-id}")
	private String githubClientId;

	@Value("${spring.security.oauth2.client.registration.github.client-secret}")
	private String githubClientSecret;

	@Value("${REFRESH_TOKEN_EXPIRE_TIME}")
	private long refreshTokenExpireTime;

	private final UserRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final RefreshTokenRepository refreshTokenRepository;

	// 부분부분
	@GetMapping("/oauth2/github")
	public void githubLogin(@RequestParam String code, HttpServletResponse response) throws IOException {

		RestTemplate tokenRequestTemplate = new RestTemplate();

		ResponseEntity<OAuthInfo> tokenResponse = tokenRequestTemplate.exchange(
			"https://github.com/login/oauth/access_token",
			HttpMethod.POST,
			getGithubToken(code),
			OAuthInfo.class);

		String accessToken = tokenResponse.getBody().getAccessToken();

		RestTemplate infoRequestTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		headers.add("Authorization", "Bearer " + accessToken);
		headers.add("Accept","application/json");

		ResponseEntity<GithubProfile> infoResponse = infoRequestTemplate.exchange(
			"https://api.github.com/user",
			HttpMethod.GET,
			new HttpEntity<>(headers),
			GithubProfile.class);

		String registerUrl = UriComponentsBuilder.fromUriString("http://localhost:5173/register")
			.build()
			//이 부분은 더 확인해봐야함
			.encode(StandardCharsets.UTF_8)
			.toUriString();

		long userId = 0;

		if(infoResponse.getBody().getEmail().isEmpty()) {

			if(infoResponse.getBody().getName().isEmpty()) throw new IllegalArgumentException("잘못된 접근입니다.");

			String name = infoResponse.getBody().getName();

			Optional<User> userOptional = userRepository.findByName(name);

			if (userOptional.isPresent()) {
				User user = userOptional.get();
				userId = user.getUserId();
			}
			//지금 oauth 로그인은 됐는데 가입된 상태가 아님
			//회원가입 페이지로 보내기
			else {
				//여기서 리다이렉트 시켜버리자
				response.sendRedirect(registerUrl);
				return;
			}

		}
		//이메일이 존재
		else {

			String email = infoResponse.getBody().getEmail();

			Optional<User> userOptional = userRepository.findByEmail(email);

			if (userOptional.isEmpty()) {
				response.sendRedirect(registerUrl);
				return;
			}
			else {
				//가입한 유저라면
				User user = userRepository.findByEmail(email).get();
				userId = user.getUserId();
			}
		}

		//토큰 생성 후 리프레시 라우터로 리다이렉트

		JwtToken token = jwtTokenProvider.generateToken(userId);

		ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", token.getRefreshToken())
			.maxAge(refreshTokenExpireTime)
			.httpOnly(true)
			.path("/")
			.build();

		//프론트로 헤더에 accessToken, 쿠키에 refreshToken 을 들고 감
		response.setHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

		//리프레시 토큰 레디스에 저장 -> 비교목적
		refreshTokenRepository.save(new RefreshToken(token.getRefreshToken()));

		String targetURL = UriComponentsBuilder.fromUriString("http://localhost:5173/refresh")
			.build()
			//이 부분은 더 확인해봐야함
			.encode(StandardCharsets.UTF_8)
			.toUriString();

		response.sendRedirect(targetURL);

	}

	private HttpEntity<MultiValueMap<String, String>> getGithubToken(String code) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("client_id", githubClientId);
		params.add("client_secret", githubClientSecret);
		params.add("code", code);

		HttpHeaders headers = new HttpHeaders();

		return new HttpEntity<>(params, headers);
	}
}
