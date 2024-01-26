package com.link.back.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailServiceTest {

	@Autowired
	private EmailService emailService;

	@Test
	@DisplayName("이메일 발신 성공")
	void sendEmail() {
		emailService.sendEmail("percyu98@gmail.com", "percyu98@gmail.com", "test from Link.", "test text");
	}
}