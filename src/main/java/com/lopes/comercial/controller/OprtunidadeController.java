package com.lopes.comercial.controller;

import com.lopes.comercial.model.Oportunidade;
import com.lopes.comercial.repository.OportunidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * The type Oprtunidade controller.
 */
@CrossOrigin // Permite chamadas de qualquer endereço.
// @CrossOrigin(value = "http://localhost:8888") // Permite chamadas somente de http://localhost:8888.
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
    public ResponseEntity<List<Oportunidade>> listar() {
        List<Oportunidade> lista = oportunidadeRepository.findAll();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
    }

    /**
     * Obter oportunidade response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<Oportunidade> obterOportunidade(@PathVariable(value = "id") Long id) {
        Optional<Oportunidade> oportunidade = oportunidadeRepository.findById(id);
        if (oportunidade.isPresent()) {
            return new ResponseEntity<>(oportunidade.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Inserir oportunidade.
     *
     * @param oportunidade the oportunidade
     * @return the oportunidade
     */
    @PostMapping
    public ResponseEntity<Oportunidade> inserir(@Valid @RequestBody Oportunidade oportunidade) {
        Optional<Oportunidade> oportunidadeExistente = oportunidadeRepository
                .findByDescricaoAndNomeProspecto(oportunidade.getDescricao(), oportunidade.getNomeProspecto());
        if (oportunidadeExistente.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(oportunidadeRepository.save(oportunidade), HttpStatus.CREATED);
    }

    /**
     * Remover response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Oportunidade> remover(@PathVariable(value = "id") Long id) {
        Optional<Oportunidade> oportunidade = oportunidadeRepository.findById(id);
        if (oportunidade.isPresent()) {
            oportunidadeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
                return new ResponseEntity<>(oportunidadeExistente.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
