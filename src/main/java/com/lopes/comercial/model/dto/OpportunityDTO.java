package com.lopes.comercial.model.dto;

import com.lopes.comercial.model.City;

import java.math.BigDecimal;
import java.util.List;

public class OpportunityDTO {

    private Long id;

    private String prospectName;

    private String opportunityDescription;

    private BigDecimal price;

    private City mainCity;

    private List<City> possibleCities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProspectName() {
        return prospectName;
    }

    public void setProspectName(String prospectName) {
        this.prospectName = prospectName;
    }

    public String getOpportunityDescription() {
        return opportunityDescription;
    }

    public void setOpportunityDescription(String opportunityDescription) {
        this.opportunityDescription = opportunityDescription;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public City getMainCity() {
        return mainCity;
    }

    public void setMainCity(City mainCity) {
        this.mainCity = mainCity;
    }

    public List<City> getPossibleCities() {
        return possibleCities;
    }

    public void setPossibleCities(List<City> possibleCities) {
        this.possibleCities = possibleCities;
    }
}
