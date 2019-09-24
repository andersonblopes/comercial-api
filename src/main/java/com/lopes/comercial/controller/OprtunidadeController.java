package com.lopes.comercial.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lopes.comercial.model.Oportunidade;
import com.lopes.comercial.repository.OportunidadeRepository;

@RestController
@RequestMapping("/oportunidades")
public class OprtunidadeController {
	
	@Autowired
	OportunidadeRepository oportunidadeRepository;

	@GetMapping
	public List<Oportunidade> listar() {
		Oportunidade oportunidade = new Oportunidade();
		oportunidade.setId(1L);
		oportunidade.setNomeProspecto("Desenvolvimento de ERP com Angular e Spring");
		oportunidade.setDescricao("Altran Portugal");
		oportunidade.setValor(new BigDecimal(345000));
		return oportunidadeRepository.findAll();
				//Arrays.asList(oportunidade);
	}

}
