package com.link.back;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@EnableScheduling

@SpringBootApplication
public class BackApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));	// EC2에서도 Tomcat 서버의 시간을 서울 시간으로 변경한다.
		SpringApplication.run(BackApplication.class, args);
	}

}
