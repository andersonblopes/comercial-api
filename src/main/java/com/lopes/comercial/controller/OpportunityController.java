package com.lopes.comercial.controller;

import com.lopes.comercial.model.City;
import com.lopes.comercial.model.Opportunity;
import com.lopes.comercial.model.dto.OpportunityDTO;
import com.lopes.comercial.repository.CityRepository;
import com.lopes.comercial.repository.OpportunityRepository;
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
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * The type Opportunity controller.
 */
@CrossOrigin // Permite chamadas de qualquer endereço.
// @CrossOrigin(value = "http://localhost:8888") // Permite chamadas somente de http://localhost:8888.
@RestController
@RequestMapping("/opportunity")
public class OpportunityController {

    /**
     * The Opportunity repository.
     */
    OpportunityRepository opportunityRepository;

    CityRepository cityRepository;

    /**
     * Instantiates a new Opportunity controller.
     *
     * @param opportunityRepository the opportunity repository
     */
    public OpportunityController(OpportunityRepository opportunityRepository, CityRepository cityRepository) {
        this.opportunityRepository = opportunityRepository;
        this.cityRepository = cityRepository;
    }

    /**
     * Find all response entity.
     *
     * @return the response entity
     */
    @GetMapping
    public ResponseEntity<List<Opportunity>> findAll() {
        List<Opportunity> opportunityList = opportunityRepository.findAll();
        if (opportunityList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(opportunityList, HttpStatus.OK);
        }
    }

    /**
     * Find by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<Opportunity> findById(@PathVariable(value = "id") Long id) {
        Optional<Opportunity> opportunity = opportunityRepository.findById(id);
        if (opportunity.isPresent()) {
            return new ResponseEntity<>(opportunity.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Insert response entity.
     *
     * @param opportunity the opportunity
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<Opportunity> insert(@Valid @RequestBody Opportunity opportunity) {
        Optional<Opportunity> opportunityFound = opportunityRepository
                .findByOpportunityDescriptionAndProspectName(opportunity.getOpportunityDescription(), opportunity.getProspectName());
        if (opportunityFound.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(opportunityRepository.save(opportunity), HttpStatus.CREATED);
    }

    /**
     * Delete response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Opportunity> delete(@PathVariable(value = "id") Long id) {
        Optional<Opportunity> opportunity = opportunityRepository.findById(id);
        if (opportunity.isPresent()) {
            opportunityRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Update response entity.
     *
     * @param opportunity the opportunity
     * @return the response entity
     */
    @PutMapping
    public ResponseEntity<Opportunity> update(@RequestBody Opportunity opportunity) {
        if (opportunity.getId() != null) {
            Optional<Opportunity> opportunityFound = opportunityRepository.findById(opportunity.getId());
            if (opportunityFound.isPresent()) {
                opportunityFound.get().setProspectName(opportunity.getProspectName());
                opportunityFound.get().setOpportunityDescription(opportunity.getOpportunityDescription());
                opportunityFound.get().setPrice(opportunity.getPrice());
                opportunityRepository.save(opportunityFound.get());
                return new ResponseEntity<>(opportunityFound.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/cities")
    public ResponseEntity<List<OpportunityDTO>> findAllIncludingCities() {
        List<Opportunity> opportunityList = opportunityRepository.findAll();

        List<City> cities = cityRepository.findAll();

        List<OpportunityDTO> opportunityDTOList = new ArrayList<>();
        for (Opportunity opportunity : opportunityList) {
            OpportunityDTO dto = new OpportunityDTO();
            dto.setOpportunityDescription(opportunity.getOpportunityDescription());
            dto.setPrice(opportunity.getPrice());
            dto.setId(opportunity.getId());
            dto.setMainCity(opportunity.getCity());
            dto.setPossibleCities(cities);

            opportunityDTOList.add(dto);
        }

        if (opportunityDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(opportunityDTOList, HttpStatus.OK);
        }
    }
}
