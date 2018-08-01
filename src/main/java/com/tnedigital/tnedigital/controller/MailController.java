package com.tnedigital.tnedigital.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tnedigital.tnedigital.domain.Email;

@Controller
public class MailController {
	
    @GetMapping("/enviar")
    public String greetingForm(Model model) {
        model.addAttribute("email", new Email());
        return "email";
    }
	
    @PostMapping("/enviar")
    public void greetingSubmit(@ModelAttribute Email email, @RequestParam("file") MultipartFile file) throws IOException {
    	String conteudo = file.getInputStream().toString();
    	System.out.println(conteudo);
        System.out.println(email.getTitulo());
        System.out.println(email.getConteudo());
        System.out.println(file.getName());
    }
	
}
