package com.tnedigital.tnedigital.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.tnedigital.tnedigital.service.impl.EmailServiceImpl;

/**
 * Configuração das propriedades de email
 * 
 * TODO Como fazer as propriedades dinamicas para ser possível utilizar
 * diferentes contas de email?
 * 
 * @author renato.caetano
 *
 */
@Configuration
@PropertySource("classpath:application.properties")
public class MailConfiguration {

	@Value("${spring.mail.protocol}")
	private String protocol;

	@Value("${spring.mail.host}")
	private String host;

	@Value("${spring.mail.port}")
	private int port;

	@Value("${spring.mail.smtp.socketFactory.port}")
	private int socketPort;

	@Value("${spring.mail.smtp.auth}")
	private boolean auth;

	@Value("${spring.mail.smtp.starttls.enable}")
	private boolean starttls;

	@Value("${spring.mail.smtp.starttls.required}")
	private boolean startlls_required;

	@Value("${spring.mail.smtp.debug}")
	private boolean debug;

	@Value("${spring.mail.smtp.socketFactory.fallback}")
	private boolean fallback;

	@Value("${spring.mail.from}")
	private String from;

	@Value("${spring.mail.username}")
	private String username;

	@Value("${spring.mail.password}")
	private String password;

	@Bean
	public EmailServiceImpl servicoEmail() {
		return new EmailServiceImpl(javaMailSender());
	}

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.auth", auth);
		mailProperties.put("mail.smtp.starttls.enable", starttls);
		mailProperties.put("mail.smtp.starttls.required", startlls_required);
		mailProperties.put("mail.smtp.socketFactory.port", socketPort);
		mailProperties.put("mail.smtp.debug", debug);
		mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		mailProperties.put("mail.smtp.socketFactory.fallback", fallback);

		mailSender.setJavaMailProperties(mailProperties);
		mailSender.setHost(host);
		mailSender.setPort(port);
		mailSender.setProtocol(protocol);
		mailSender.setUsername(username);
		mailSender.setPassword(password);

		return mailSender;
	}

}
