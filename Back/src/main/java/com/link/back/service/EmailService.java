package com.link.back.service;

import org.eclipse.angus.mail.util.logging.MailHandler;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender javaMailSender;

	public void sendEmail(String fromEmail, String toEmail, String subject, String text) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
			mimeMessageHelper.setFrom(fromEmail);
			mimeMessageHelper.setTo(toEmail);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(text, true);
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new RuntimeException(); // todo: create Exception
		}
	}
}
