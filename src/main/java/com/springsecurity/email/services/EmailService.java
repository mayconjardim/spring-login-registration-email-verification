package com.springsecurity.email.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.springsecurity.email.components.EmailSender;

@Service

public class EmailService implements EmailSender {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
	
	@Override
	@Async
	public void send(String to, String email) {
		
		try {
		
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			helper.setText(email, true);
			helper.setTo(to);
			helper.setSubject("Confirm your email");
			helper.setFrom("mayconsmill@gmail.com");
			
		} catch (MessagingException e) {
			LOGGER.error("Failed to send email", e );
			throw new IllegalStateException("Failed to send email");
		}
		
	}

}
