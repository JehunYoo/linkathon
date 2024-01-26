package com.link.back.service;


import com.link.back.dto.*;

public interface UserService {
    String signup(UserSignUpDto userSignUpDto) throws Exception;
//    @Transactional

    //추가정보 받는 메소드
//    void AdditionalInfo(String Token, AdditionalUserInfoRequest additionalInfoRequest);

    //User 정보 수정
    //토큰이 맞는지 확인하고 받은거 그대로 배출
//    void updateUser(String token, UserInfoRequest userInfoRequest) throws Exception;


//    Long deleteUser(String token);
    JwtToken login(LoginRequest loginRequest);

    void resetPassword(UserPasswordResetRequest userPasswordResetRequest);

    //id로 User 받아오는 메소드
//    UserDtoResponse getUser(String token);
//    Long deleteRefreshToken(String token);

}