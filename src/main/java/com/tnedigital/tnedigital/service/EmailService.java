package com.tnedigital.tnedigital.service;

import javax.mail.MessagingException;

import com.tnedigital.tnedigital.domain.Email;

/**
 * Interface com os métodos úteis para envio de email
 * @author renato.caetano
 *
 */
public interface EmailService {
	
	public void sendEmail(Email email); 
	
	public void sendEmailWithAttachment(Email email) throws MessagingException;
	
}
