package com.link.back.service.impl;

import com.link.back.dto.JwtToken;
import com.link.back.dto.LoginRequest;
import com.link.back.dto.UserPasswordResetRequest;
import com.link.back.dto.UserSignUpDto;
import com.link.back.entity.User;
import com.link.back.repository.UserRepository;
import com.link.back.security.JwtTokenProvider;
import com.link.back.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
//    private final TokenRepository tokenRepository;
//    private final ModelMapper mapper;

    @Override
    public String signup(UserSignUpDto userSignUpDto) throws Exception {

        if (userRepository.findByEmail(userSignUpDto.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        User user = User.builder()
                .email(userSignUpDto.getEmail())
                .password(userSignUpDto.getPassword())
                .name(userSignUpDto.getName())
                .gender(userSignUpDto.isGender())
                .birth(userSignUpDto.getBirth())
                .phoneNumber(userSignUpDto.getPhoneNumber())
                .rating(userSignUpDto.getRating())
                .build();


        System.out.println(passwordEncoder.encode(userSignUpDto.getPassword()));
        userRepository.save(user);
        return "회원가입 성공";

    }

    @Override
    public JwtToken login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));

        if (!loginRequest.getPassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

//        if (!passwordEncoder.encode(loginRequest.getPassword()).equals(user.getPassword())) {
//            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
//        }

        JwtToken jwtToken = jwtTokenProvider.generateToken(user.getUserId());

        //리프레쉬 토큰 부분 ->
//      tokenRepository.save(new Token(jwtToken.getRefreshToken()));
        return jwtToken;
    }

    //어처피 email 확인 후에 하는거라서 어처피 노상관
    @Override
    public void resetPassword(UserPasswordResetRequest userPasswordResetRequest) {
        String email = userPasswordResetRequest.getEmail();
        String password = userPasswordResetRequest.getPassword();

        User user = userRepository.findByEmail(email).get();
        //이전 비밀번호랑 같으면 밴해주기
        if(user.getPassword().equals(password)) throw new IllegalArgumentException("이전 비밀번호와 다르게 설정해야합니다.");

        user.updatePassword(password);

    }




}
