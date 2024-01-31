package com.link.back.config.OAuth;

import com.link.back.dto.JwtToken;
import com.link.back.repository.UserRepository;
import com.link.back.security.JwtTokenProvider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

   @Value("${JWT_SECRET_KEY}")
   private String secretKey;

   @Value("${ACCESS_TOKEN_EXPIRE_TIME}")
   private Long accessTokenExpiredMs;

   @Value("${REFRESH_TOKEN_EXPIRE_TIME}")
   private Long refreshTokenExpiredMs;

   private final UserRepository userRepository;
   private final JwtTokenProvider jwtTokenProvider;


   @Override
   public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, SecurityException{

       OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
       String email = oAuth2User.getAttribute("email");
       //어디 플랫폼인지 가져오는건데 필요한지 의문임
       String provider = oAuth2User.getAttribute("provider");
       //존재하는 User인 경우
       boolean isExist = oAuth2User.getAttribute("exist");

       if(isExist){
           Long userId = userRepository.findByEmail(email).get().getUserId();
           //회원이 존재할 때 토큰 발급, 리프레쉬 토큰 추가로 넣어야함
           JwtToken token = jwtTokenProvider.generateToken(userId);

           //로그인 성공페이지 url 확인해서 받아야함
           String targetUrl = UriComponentsBuilder.fromUriString("https://www.google.com/")
                   .queryParam("accessToken", token)
                   .build()
                   //이 부분은 더 확인해봐야함
                   .encode(StandardCharsets.UTF_8)
                   .toUriString();
           // 로그인 확인 페이지로 리다이렉트 시킨다.
           getRedirectStrategy().sendRedirect(request, response, targetUrl);
       }

   }


}
