package com.lopes.comercial.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * The type Opportunity.
 */
@Entity
public class Opportunity {

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The Prospect name.
     */
    @NotEmpty
    @Size(max = 80)
    @Column(name = "prospect_name")
    private String prospectName;

    /**
     * The Opportunity description.
     */
    @NotEmpty
    @Size(max = 200)
    @Column(name = "opportunity_description")
    private String opportunityDescription;

    /**
     * The Price.
     */
    @Min(0)
    @NotNull
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "fk_city")
    private City city;


    /**
     * Instantiates a new Opportunity.
     */
    public Opportunity() {

    }

    /**
     * Instantiates a new Opportunity.
     *
     * @param prospectName           the prospect name
     * @param opportunityDescription the opportunity description
     * @param price                  the price
     */
    public Opportunity(String prospectName, String opportunityDescription, BigDecimal price) {
        this.prospectName = prospectName;
        this.opportunityDescription = opportunityDescription;
        this.price = price;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets prospect name.
     *
     * @return the prospect name
     */
    public String getProspectName() {
        return prospectName;
    }

    /**
     * Sets prospect name.
     *
     * @param nomeProspecto the nome prospecto
     */
    public void setProspectName(String nomeProspecto) {
        this.prospectName = nomeProspecto;
    }

    /**
     * Gets opportunity description.
     *
     * @return the opportunity description
     */
    public String getOpportunityDescription() {
        return opportunityDescription;
    }

    /**
     * Sets opportunity description.
     *
     * @param descricao the descricao
     */
    public void setOpportunityDescription(String descricao) {
        this.opportunityDescription = descricao;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param valor the valor
     */
    public void setPrice(BigDecimal valor) {
        this.price = valor;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Opportunity that = (Opportunity) o;
        return id.equals(that.id);
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "Opportunity{" +
                "id=" + id +
                ", prospectName='" + prospectName + '\'' +
                ", opportunityDescription='" + opportunityDescription + '\'' +
                ", value=" + price +
                '}';
    }
}
