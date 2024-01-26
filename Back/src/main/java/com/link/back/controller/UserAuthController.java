package com.link.back.controller;

import com.link.back.dto.UserSignUpDto;
import com.link.back.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class UserAuthController {

    private UserService userService;
//    private JwtTokenProvider jwtTokenProvider;

    public UserAuthController(UserService userService) {
        this.userService = userService;
    }

    //권한인증 완료
    @GetMapping
    public ResponseEntity<String> hi(@RequestHeader("Authorization") String token) throws Exception {
        System.out.println("히히 왔지롱");
        return null;
    }
    //회원 탈퇴
    //회원 정보 수정
    //회원 추가 정보 입력



}
