package com.tnedigital.tnedigital.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class KaueController {
	
	@RequestMapping("/cadastro")
	@ResponseBody
	String paginaInicial() {
		return "Olá Kaue, seja bem vindo à sua primeira aplicação em SPRING BOOT";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(KaueController.class, args);
	}

}
