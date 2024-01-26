package com.link.back.controller;


import com.link.back.dto.*;
import com.link.back.dto.request.UserFindEmailRequest;
import com.link.back.dto.request.UserPasswordResetRequest;
import com.link.back.repository.RefreshTokenRepository;
import com.link.back.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserNonAuthController {

    private UserService userService;
    private RefreshTokenRepository refreshTokenRepository;

    public UserNonAuthController(UserService userService, RefreshTokenRepository refreshTokenRepository ) {
        this.userService = userService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    //회원 가입
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody UserSignUpDto userSignUpDto) throws Exception {
        String response = userService.signup(userSignUpDto);
        log.info("Response: {}", response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<JwtToken> login(@Valid @RequestBody LoginRequest loginRequest){

        JwtToken jwtToken = userService.login(loginRequest);

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();

        // AUTHORIZATION에 Access 토큰을 넣음
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken.getAccessToken());

        // ResponseCookie 객체를 생성하고, 쿠키에 Refresh 토큰을 설정
        ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", jwtToken.getRefreshToken())
                .maxAge(7 * 24 * 60 * 60)  // 7 days expiration
                .httpOnly(true)
//                .sameSite("None") // SameSite 속성을 제3자 쿠키에 대해 None으로 설정
//                .secure(true) // 필수로 같이 설정해줘야함
                .path("/")
                .build();

        // refresh 토큰을 넣은 쿠키를 헤더에 담아서 보냄
        headers.add(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

        // refresh 토큰을 redis에 넣어줌
        RefreshToken refreshToken = new RefreshToken(jwtToken.getAccessToken());
        refreshTokenRepository.save(refreshToken);

        // ResponseEntity를 생성하고, 헤더와 상태 코드를 설정
        return ResponseEntity.ok()
                .headers(headers)
                .build();
    }


    //메일인증확인

    //이메일 찾기
    @PostMapping("/email")
    public ResponseEntity<String> findEmail (@Valid @RequestBody UserFindEmailRequest userFindEmailRequest) throws Exception {

        //여기서 보냄

        // userService.resetPassword(userFindEmailRequest);

        //로그인 페이지로 보내주기
        return new ResponseEntity<>("비밀번호 변경 완료", HttpStatus.CREATED);
    }

    //비밀번호 찾기
    //로직 바꾸기
    @PostMapping("/password")
    public ResponseEntity<String> changePassword (@Valid @RequestBody UserPasswordResetRequest userPasswordResetRequest) throws Exception {

        userService.resetPassword(userPasswordResetRequest);

        //로그인 페이지로 보내주기
        return new ResponseEntity<>("비밀번호 변경 완료", HttpStatus.CREATED);
    }

    //경력인증

}
