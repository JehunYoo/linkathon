package com.link.back.config.OAuth;

import java.util.HashMap;
import java.util.Map;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OAuth2Attribute {
    private Map<String, Object> attributes; // 사용자 속성 정보를 담는 Map
    private String attributeKey;
    private String email;
    private String name;
    private String provider;

    @Builder
    public OAuth2Attribute(Map<String, Object> attributes, String attributeKey, String email, String name,
        String provider) {
        this.attributes = attributes;
        this.attributeKey = attributeKey;
        this.email = email;
        this.name = name;
        this.provider = provider;
    }

    static OAuth2Attribute of(String provider, String attributeKey,
        Map<String, Object> attributes) {
        switch (provider) {
            case "google":
                return ofGoogle(provider, attributeKey, attributes);
            case "github":
                return ofGithub(provider,"email", attributes);
            default:
                throw new RuntimeException();
        }
    }


    //Google 로그인일 경우 사용하는 메서드, 바로 get() 적용가능

    private static OAuth2Attribute ofGoogle(String userNameAttributeName, String attributeKey, Map<String, Object> attribute){

        return OAuth2Attribute.builder()
            .name(String.valueOf(attribute.get("name")))
            .email(String.valueOf(attribute.get("email")))
            .attributes(attribute)
            .attributeKey(userNameAttributeName)
            .build();
    }

    //github도 Google처럼 바로 get 가능
    private static OAuth2Attribute ofGithub(String provider, String attributeKey, Map<String, Object> attributes){

        System.out.println(attributes.toString());

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
