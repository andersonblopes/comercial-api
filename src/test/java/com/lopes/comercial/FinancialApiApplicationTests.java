package com.lopes.comercial;

import com.lopes.comercial.model.Opportunity;
import com.lopes.comercial.repository.OpportunityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * The type Financial api application tests.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class FinancialApiApplicationTests {

    /**
     * The Entity manager.
     */
    @Autowired
    private TestEntityManager entityManager;

    /**
     * The Opportunity repository.
     */
    @Autowired
    private OpportunityRepository opportunityRepository;


    /**
     * Must find opportunity.
     */
    @Test
    public void mustFindOpportunity() {
        // given
        Opportunity opportunity = new Opportunity("Teste", "Teste prospecto", new BigDecimal(35000L));
        entityManager.persist(opportunity);
        entityManager.flush();

        // when
        Optional<Opportunity> opportunityFound = opportunityRepository
                .findByOpportunityDescriptionAndProspectName(opportunity.getOpportunityDescription(), opportunity.getProspectName());

        // then
        String description = opportunityFound.get().getOpportunityDescription();
        assertThat(description).isEqualTo(opportunity.getOpportunityDescription());
    }

}
