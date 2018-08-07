package com.tnedigital.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tnedigital.configuration.MailConfiguration;
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
	 * @throws MessagingException
	 */
	@Async
	public void send(Email email, List<InternetAddress> addresses) throws MessagingException {
		for (InternetAddress address : addresses) {
			sendMessage(email, address);
		}
	}

	/**
	 * Enviar e-mail simples para um endereço
	 * 
	 * @param message
	 * @param address
	 * @throws MessagingException
	 */
	public void sendMessage(Email email, InternetAddress address) throws MessagingException {
		MimeMessage message = createMessage(email, address);
		emailSender.send(message);
	}

	/**
	 * Define a lista de endereços através do arquivo importado
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public List<InternetAddress> defineAddress(MultipartFile file) throws IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(file.getInputStream()));
		List<InternetAddress> addressList = new ArrayList<InternetAddress>();
		String line;

		while ((line = buffer.readLine()) != null) {
			try {
				if (!addressList.contains(line)) {
					InternetAddress address = new InternetAddress();
					address.setAddress(line);
					address.validate();

					if (addressList.contains(address)) {
						continue;
					} else {
						addressList.add(address);
					}
				}
			} catch (AddressException e) {
				System.out.println("Endereço inválido: " + e.getRef());
			}

		}

		return addressList;
	}

	/**
	 * Cria a mensagem de e-mail
	 * 
	 * @param email
	 * @return
	 * @throws MessagingException
	 */
	public MimeMessage createMessage(Email email, InternetAddress address) throws MessagingException {
		final MimeMessage mimeMessage = emailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		
		message.setFrom(mailConfiguration.getUsername());
		message.setSubject(email.getSubject());
		message.setText(email.getText(), true);
		message.setTo(address);
		
		return mimeMessage;
	}

}
