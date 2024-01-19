package com.link.back.jwt;

import java.util.Base64;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;


@Slf4j
@Component
@RequiredArgsConstructor
public class JWTTokenProvider {

    @Value("${JWT_SECRET_KEY}")
	private String secretKey;

	@Value("${ACCESS_TOKEN_EXPIRE_TIME}")
	private Long tokenValidTime;


	@Value("${REFRESH_TOKEN_EXPIRE_TIME}")
	private Long refreshTokenValidTime;

	private final UserDetailsService userDetailsService;

	@PostConstruct
	protected void init(){
		//시크릿 키를 변환해주는 메소드
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	// 토큰 생성 메소드 이메일로 설정은 했는데 id로 할수도 있음
	// user_id를 필수로 담아야함

	public String createToken(String email) {
		Claims claims = Jwts.claims().setSubject(email);
		Date now = new Date();

		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(now)
			.setExpiration(new Date(now.getTime() + tokenValidTime))
			.signWith(SignatureAlgorithm.HS256, secretKey)
			.compact();
	}

	//refresh 토큰 생성 메소드
	public String createRefreshToken() {
		Date now = new Date();

		return Jwts.builder()
			.setIssuedAt(now)
			.setExpiration(new Date(now.getTime() + refreshTokenValidTime))
			.signWith(SignatureAlgorithm.HS256, secretKey)
			.compact();
	}

	//리프레시 토큰에서
	public String getMemberId(String token) {
		try {
			return Jwts.parserBuilder()
				.setSigningKey(secretKey)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
		} catch (ExpiredJwtException e) {
			return e.getClaims().getSubject();
		}
	}
	public Authentication getAuthentication (String token){
		UserDetails userDetails = userDetailsService.loadUserByUsername(getMemberId(token));
		// 가운데 빈 칸은 비밀번호가 들어가는 위치 하지만 실제로 비밀번호가 필요한것은 아니기 때문에 빈칸으로 대체 좀 더 생각할 필요가 있음
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}


}
