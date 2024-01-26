package com.link.back.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailServiceTest {

	@Autowired
	private EmailService emailService;

	@Value("${spring.mail.sender}")
	String email;

	@Test
	@DisplayName("이메일 발신 성공")
	void sendEmail() {
		emailService.sendEmail(email, email, "test from Link.", "<p>test text<p/>");
	}
}