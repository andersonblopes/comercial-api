package com.lopes.comercial.controller;

import java.util.List;
import java.util.Optional;

import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.lopes.comercial.model.Oportunidade;
import com.lopes.comercial.repository.OportunidadeRepository;

import pt.digitalis.poc.Producer;

/**
 * The type Oprtunidade controller.
 */
@CrossOrigin(value = "http://localhost:8888")
@RestController
@RequestMapping("/oportunidades")
public class OprtunidadeController {

	/**
	 * The Oportunidade repository.
	 */
	@Autowired
	OportunidadeRepository oportunidadeRepository;

	/**
	 * Listar list.
	 *
	 * @return the list
	 * @throws JMSException
	 * @throws NamingException
	 */
	@GetMapping
	public List<Oportunidade> listar() throws NamingException, JMSException {
		List<Oportunidade> lista = oportunidadeRepository.findAll();
		Producer queue = new Producer();
		queue.toSend(lista);
		return lista;
	}

	/**
	 * Obter oportunidade response entity.
	 *
	 * @param id the id
	 *
	 * @return the response entity
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Oportunidade> obterOportunidade(@PathVariable Long id) {
		Optional<Oportunidade> oportunidade = oportunidadeRepository.findById(id);
		if (!oportunidade.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oportunidade.get());
	}

	/**
	 * Inserir oportunidade.
	 *
	 * @param oportunidade the oportunidade
	 *
	 * @return the oportunidade
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Oportunidade inserir(@Valid @RequestBody Oportunidade oportunidade) {
		Optional<Oportunidade> oportunidadeExistente = oportunidadeRepository
				.findByDescricaoAndNomeProspecto(oportunidade.getDescricao(), oportunidade.getNomeProspecto());

		if (oportunidadeExistente.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Oportunidade já cadastrada!");
		}

		return oportunidadeRepository.save(oportunidade);
	}

	/**
	 * Remover response entity.
	 *
	 * @param id the id
	 *
	 * @return the response entity
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Oportunidade> remover(@PathVariable Long id) {

		Optional<Oportunidade> oportunidade = oportunidadeRepository.findById(id);

		if (!oportunidade.isPresent()) {
			// throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Oportunidade não
			// encontrada na base de dados.");
			return ResponseEntity.notFound().build();
		}

		oportunidadeRepository.deleteById(id);
		return ResponseEntity.noContent().build();

	}

	/**
	 * Atualizar response entity.
	 *
	 * @param oportunidade the oportunidade
	 *
	 * @return the response entity
	 */
	@PutMapping
	public ResponseEntity<Oportunidade> atualizar(@RequestBody Oportunidade oportunidade) {
		if (oportunidade.getId() != null) {
			Optional<Oportunidade> oportunidadeExistente = oportunidadeRepository.findById(oportunidade.getId());

			if (oportunidadeExistente.isPresent()) {
				oportunidadeExistente.get().setNomeProspecto(oportunidade.getNomeProspecto());
				oportunidadeExistente.get().setDescricao(oportunidade.getDescricao());
				oportunidadeExistente.get().setValor(oportunidade.getValor());
				oportunidadeRepository.save(oportunidadeExistente.get());
				return ResponseEntity.ok(oportunidadeExistente.get());
			}

		}

		return ResponseEntity.notFound().build();
	}

}
