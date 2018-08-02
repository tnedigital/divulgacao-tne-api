package com.tnedigital.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.tnedigital.service.impl.EmailServiceImpl;

/**
 * Configuração das propriedades de email
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

	@Value("${spring.mail.properties.mail.smtp.auth}")
	private boolean auth;

	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private boolean starttls;

	@Value("${spring.mail.properties.mail.smtp.starttls.required}")
	private boolean startlls_required;
	
	@Value("${spring.mail.properties.mail.smtp.socketFactory.port}")
	private int socketPort;

	@Value("${spring.mail.properties.mail.smtp.socketFactory.fallback}")
	private boolean fallback;

	@Value("${spring.mail.username}")
	private String username;

	@Value("${spring.mail.password}")
	private String password;

	@Bean
	public EmailServiceImpl emailServiceImpl() {
		return new EmailServiceImpl(javaMailSender());
	}
	
	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");

		mailSender.setJavaMailProperties(props);
		mailSender.setHost(host);
		mailSender.setPort(port);
		mailSender.setProtocol(protocol);
		mailSender.setUsername(username);
		mailSender.setPassword(password);

		return mailSender;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isAuth() {
		return auth;
	}

	public void setAuth(boolean auth) {
		this.auth = auth;
	}

	public boolean isStarttls() {
		return starttls;
	}

	public void setStarttls(boolean starttls) {
		this.starttls = starttls;
	}

	public boolean isStartlls_required() {
		return startlls_required;
	}

	public void setStartlls_required(boolean startlls_required) {
		this.startlls_required = startlls_required;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
