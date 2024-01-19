package com.link.back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 인증이 필요없는 요청 URL
    // 경력인증, 로그인, 회원가입
    // 추가적으로 인증없이 사용할 수 있는 기능에 대해서 배열관리하기
    private static final String[] WHITE_LIST_URL = {
            "/api/users/**",
    };

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

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
                //폼 로그인 사용 X -> 이건 질문할거임
                .formLogin(AbstractHttpConfigurer::disable)
                //베이직 접근차단
                .httpBasic(AbstractHttpConfigurer::disable)
                //토큰 감지 기능이 필요한데 좀 막힘
                //.addFilterBefore(new JwtFilter(setting), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                            //추가적으로
                            .anyRequest().authenticated()
                );


        return http.build();
    }


}
