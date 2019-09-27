package com.lopes.comercial.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.lopes.comercial.model.Oportunidade;
import com.lopes.comercial.repository.OportunidadeRepository;

@RestController
@RequestMapping("/oportunidades")
public class OprtunidadeController {

	@Autowired
	OportunidadeRepository oportunidadeRepository;

	@GetMapping
	public List<Oportunidade> listar() {
		return oportunidadeRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Oportunidade> obterOportunidade(@PathVariable Long id) {
		Optional<Oportunidade> oportunidade = oportunidadeRepository.findById(id);
		if (!oportunidade.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oportunidade.get());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Oportunidade inserir(@Valid @RequestBody Oportunidade oportunidade) {
		Optional<Oportunidade> oportunidadeExistente = oportunidadeRepository
				.findByDescricaoAndNomeProspecto(oportunidade.getDescricao(), oportunidade.getNomeProspecto());

		if (oportunidadeExistente.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Oportunidade já cadastrada na base de dados.");
		}

		return oportunidadeRepository.save(oportunidade);
	}

	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		oportunidadeRepository.deleteById(id);
	}

}
