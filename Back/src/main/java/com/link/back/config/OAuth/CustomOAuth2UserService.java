package com.link.back.config.OAuth;

import com.link.back.entity.User;
import com.link.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

   private final UserRepository userRepository;

   @Override
   public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
       //기본 OAuth2UserService 객체 생성 후
       //OAuth2UserService를 사용하여 OAuth2User 정보를 가져온다.
       OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
       OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

       // 클라이언트 등록 ID(google, github)와 사용자 이름 속성을 가져온다.
       String registrationId = userRequest.getClientRegistration().getRegistrationId();
       String userNameAttributeName = userRequest.getClientRegistration()
           .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

       // OAuth2UserService를 사용하여 가져온 OAuth2User 정보로 OAuth2Attribute 객체를 만든다.
       OAuth2Attribute oAuth2Attribute =
           OAuth2Attribute.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

       //oauth2의 attribute
       Map<String, Object> userAttribute = oAuth2Attribute.convertToMap();

       String email = (String) userAttribute.get("email");
       // 이미 가입된 회원인지 조회
       Optional<User> findUser = userRepository.findByEmail(email);

       if(findUser.isEmpty()){
           //회원이 존재하지 않으면 존재 유무 추가
           userAttribute.put("exist", false);

           //DefaultOAuth2User 객체에 권한 담지 않고 그냥 email 보냄
           return new DefaultOAuth2User(
                   Collections.emptySet(),
                   userAttribute,
                   "email"
           );
       }
       //회원이 존재할경우 true를 담아서 보냄
       userAttribute.put("exist", true);

       //DefaultOAuth2User 객체에 권한 담지 않고 그냥 email 보냄
       return new DefaultOAuth2User(
               Collections.emptySet(),
               userAttribute,
               "email"
       );
   }
}
