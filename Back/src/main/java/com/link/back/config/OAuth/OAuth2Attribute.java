package com.link.back.config.OAuth;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@ToString
@Builder(access = AccessLevel.PRIVATE) // Builder 메서드를 외부에서 사용하지 않기 때문에 private 제어자 사용
@Getter
public class OAuth2Attribute {
    private Map<String, Object> attributes; // 사용자 속성 정보를 담는 Map
    private String attributeKey;
    private String email;
    private String name;
    private String provider;

    static OAuth2Attribute of(String provider, String attributeKey, Map<String, Object> attributes){

        switch (provider){
            case "google":
                return ofGoogle(provider, attributeKey, attributes);
            case "github":
                return ofGithub(provider, attributeKey, attributes);
            default:
                throw new RuntimeException();

        }
    }

    //Google 로그인일 경우 사용하는 메서드, 바로 get() 적용가능

    private static OAuth2Attribute ofGoogle(String provider, String attributeKey, Map<String, Object> attributes){

        return OAuth2Attribute.builder()
                .email((String) attributes.get("email"))
                .provider(provider)
                .attributes(attributes)
                .attributeKey(attributeKey)
                .build();
    }

    //github도 Google처럼 바로 get 가능
    private static OAuth2Attribute ofGithub(String provider, String attributeKey, Map<String, Object> attributes){
        return OAuth2Attribute.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .provider(provider)
                .attributes(attributes)
                .attributeKey(attributeKey)
                .build();
    }

    // OAuth2User 객체에 넣어주기 위해서 Map으로 값들을 반환
    Map<String, Object> convertToMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", attributeKey);
        map.put("key", attributeKey);
        map.put("email", email);
        map.put("provider", provider);

        return map;
    }
}
