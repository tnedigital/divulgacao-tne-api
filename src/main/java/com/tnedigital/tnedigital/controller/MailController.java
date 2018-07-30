package com.tnedigital.tnedigital.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
	
	@ResponseBody
	@RequestMapping(value="/teste", method=RequestMethod.POST)
	public String teste() {
		return "Olá mundo";
	}
	
	

}
