package com.lopes.comercial.repository;

import com.lopes.comercial.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface Opportunity repository.
 */
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {


    /**
     * Find by opportunity description and prospect name optional.
     *
     * @param opportunityDescription the opportunity description
     * @param prospectName           the prospect name
     * @return the optional
     */
    Optional<Opportunity> findByOpportunityDescriptionAndProspectName(String opportunityDescription, String prospectName);

}
