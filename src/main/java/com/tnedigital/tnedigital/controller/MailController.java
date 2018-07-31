package com.tnedigital.tnedigital.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tnedigital.tnedigital.domain.Email;

@RestController
public class MailController {
	
	@ResponseBody
	@RequestMapping(value="/teste", method=RequestMethod.POST)
	public String teste() {
		return "Olá mundo";
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public void processUpload(@RequestParam MultipartFile file) throws IOException {
	        System.out.println("Upload de arquivo");
	        System.out.println(file.getName());
	}
	
    @PostMapping("/enviar")
    public void greetingSubmit(@ModelAttribute Email email) {
        System.out.println(email.getTitulo());
        System.out.println(email.getConteudo());
    }
	
}
