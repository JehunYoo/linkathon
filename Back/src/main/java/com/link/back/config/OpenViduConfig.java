package com.link.back.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.openvidu.java.client.OpenVidu;

//@Configuration
public class OpenViduConfig {

	@Value("${OPENVIDU_URL}") // 오픈비두 서버 URL
	private String OPENVIDU_URL;

	@Value("${OPENVIDU_SECRET}") // 인증용 비밀키
	private String OPENVIDU_SECRET;

	@Bean
	public OpenVidu openVidu() {
		return new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
	}
}
