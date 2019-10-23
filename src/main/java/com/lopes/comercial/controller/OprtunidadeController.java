package com.lopes.comercial.controller;

import com.lopes.comercial.model.Oportunidade;
import com.lopes.comercial.repository.OportunidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * The type Oprtunidade controller.
 */
@CrossOrigin("http://localhost:4200")
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
     */
    @GetMapping
    public List<Oportunidade> listar() {
        return oportunidadeRepository.findAll();
    }

    /**
     * Obter oportunidade response entity.
     *
     * @param id the id
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
