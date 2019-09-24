package com.lopes.comercial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oportunidades")
public class OprtunidadeController {

	@GetMapping
	public String listar() {
		return "Hello!";
	}

}
