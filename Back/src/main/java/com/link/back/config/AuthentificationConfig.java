package com.link.back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthentificationConfig {



    // 인증이 필요없는 요청 URL
    private static final String[] WHITE_LIST_URL = {
            "/api/users/**",
    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        //csrf 방어기능 해제 6.1 버전부터 Method Reference 연산자(::)로
        http
                .csrf(AbstractHttpConfigurer::disable)
                //cors 꼭 써줘야하나?
                .cors(AbstractHttpConfigurer::disable)
                //세션 사용 없이 stateless 서버로 만들기
                .sessionManagement((sessionManagement) ->
                                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                //로그인폼 사용 X -> 이건 질문할거임
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                //토큰 감지 기능이 필요한데 좀 막힘 여기서
                .addFilterBefore(new JwtFilter(setting), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                );


        return http.build();
    }


}
