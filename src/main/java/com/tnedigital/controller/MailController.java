package com.tnedigital.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tnedigital.domain.Address;
import com.tnedigital.domain.Email;
import com.tnedigital.service.EmailService;

/**
 * Controlador das requisições de envio dos e-mails
 * 
 * @author renato.caetano
 */
@Controller
public class MailController {

	@Autowired
	private EmailService emailService;

	/**
	 * Abre a página para editar e-mail
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/enviar")
	public String emailForm(Model model) {
		model.addAttribute("email", new Email());
		return "email";
	}

	/**
	 * Submissão do e-mail com o arquivo de contatos via formulário
	 * 
	 * @param email
	 * @param file
	 * @throws IOException
	 */
	@PostMapping("/enviar")
	public void emailSubmit(@ModelAttribute Email email, @RequestParam("file") MultipartFile file) throws IOException {
		List<Address> addresses = emailService.defineAddress(file);
		emailService.send(email, addresses);
	}

}
