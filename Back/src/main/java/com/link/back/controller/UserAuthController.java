package com.link.back.controller;

import java.util.List;

import com.link.back.dto.UserSignUpDto;
import com.link.back.dto.request.AdditionalUserInfoRequest;
import com.link.back.dto.request.UserUpdateInfoRequest;
import com.link.back.dto.response.UserInfoResponsse;
import com.link.back.entity.UserSkill;
import com.link.back.service.UserService;

import io.lettuce.core.dynamic.annotation.Param;
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

    //정보조회
    @GetMapping("/users")
    public ResponseEntity<UserInfoResponsse> getInfo(@RequestHeader("Authorization") String token) throws Exception {

        UserInfoResponsse result = userService.getInfo(token);

        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    //회원 탈퇴
    @DeleteMapping("/users")
    public ResponseEntity<String> deleteAccount(@RequestHeader("Authorization") String token){

        userService.deleteUser(token);

        return new ResponseEntity<>("회원탈퇴가 완료되었습니다.", HttpStatus.ACCEPTED);
    }

    //회원 정보 수정
    @PutMapping("/users")
    public ResponseEntity<String> updateUser(@RequestHeader("Authorization") String token, @Valid @RequestBody UserUpdateInfoRequest userUpdateInfoRequest){

        userService.updateInfo(token, userUpdateInfoRequest);

        return new ResponseEntity<>("갱신되었습니다.", HttpStatus.ACCEPTED);
    }

    //회원 추가 정보 입력
    @PostMapping("/users/addtionalinfo")
    public ResponseEntity<String> addInfo(@RequestHeader("Authorization") String token, @Valid @RequestBody AdditionalUserInfoRequest additionalUserInfoRequest){

        userService.updateAdditionalInfo(token, additionalUserInfoRequest);

        return new ResponseEntity<>("추가정보입력이 완료되었습니다.", HttpStatus.ACCEPTED);
    }

}
