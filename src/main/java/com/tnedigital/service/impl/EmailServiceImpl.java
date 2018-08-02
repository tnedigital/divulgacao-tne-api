package com.tnedigital.service.impl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.tnedigital.configuration.MailConfiguration;
import com.tnedigital.domain.Email;
import com.tnedigital.service.EmailService;

@Component
public class EmailServiceImpl implements EmailService {

	private final JavaMailSender emailSender;
	
	@Autowired
	private MailConfiguration mailConfiguration;

	@Autowired
	public EmailServiceImpl(final JavaMailSender emailSender) {
		this.emailSender = emailSender;
		System.setProperty("mail.mime.charset", "iso-8859-1");
	}

	@Override
	public void sendEmail(Email email) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(mailConfiguration.getUsername());
		message.setTo(email.getRecipient());
		message.setSubject(email.getSubject());
		message.setText(email.getText());
		emailSender.send(message);
	}

	@Override
	public void sendEmailWithAttachment(Email email) throws MessagingException {
		try {

			MimeMessage message = emailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo(email.getRecipient());
			helper.setSubject(email.getSubject());
			helper.setText(email.getText());

			FileSystemResource file = new FileSystemResource(new File(""));
			helper.addAttachment("Invoice", file);

			emailSender.send(message);

		} catch (MessagingException e) {
			System.out.println(e);
		}

	}
}
