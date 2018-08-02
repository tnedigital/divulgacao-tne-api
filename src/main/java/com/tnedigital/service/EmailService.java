package com.tnedigital.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tnedigital.configuration.MailConfiguration;
import com.tnedigital.domain.Address;
import com.tnedigital.domain.Email;

/**
 * Serviço responsável pelo envio dos e-mails
 * 
 * @author renato.caetano
 */
@Service
public class EmailService {

	private final JavaMailSender emailSender;

	@Autowired
	private MailConfiguration mailConfiguration;

	@Autowired
	public EmailService(final JavaMailSender emailSender) {
		this.emailSender = emailSender;
		System.setProperty("mail.mime.charset", "iso-8859-1");
	}

	/**
	 * Executa o fluxo de envio de e-mails
	 * 
	 * @param email
	 * @param addresses
	 */
	@Async
	public void send(Email email, List<Address> addresses) {
		SimpleMailMessage message = createMessage(email);
		for (Address address : addresses) {
			sendMessage(message, address);
		}
	}

	/**
	 * Enviar e-mail simples para um endereço
	 * 
	 * @param message
	 * @param address
	 */
	public void sendMessage(SimpleMailMessage message, Address address) {
		message.setTo(address.getAddress());
		emailSender.send(message);
	}

	/**
	 * TODO Enviar e-mail com anexo
	 * 
	 * @param email
	 * @throws MessagingException
	 */
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

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Define a lista de endereços através do arquivo importado
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public List<Address> defineAddress(MultipartFile file) throws IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(file.getInputStream()));
		List<Address> addressList = new ArrayList<>();
		String line;

		while ((line = buffer.readLine()) != null) {
			if (!addressList.contains(line)) {
				Address address = new Address();
				address.setAddress(line);
				addressList.add(address);
			}
		}

		return addressList;
	}

	/**
	 * Cria a mensagem de e-mail
	 * 	// TODO Configurar o conteúdo da mensagem em formato de HTML
	 * @param email
	 * @return
	 */
	public SimpleMailMessage createMessage(Email email) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(mailConfiguration.getUsername());
		message.setSubject(email.getSubject());
		message.setText(email.getText());
		return message;
	}

}
