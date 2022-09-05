package com.nordea.assigment.countryservice.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="countries")
public class CountryEntity {
    @Id
    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "country_name")
    private String countryName;

    @Column(name="country_capital")
    private String countryCapital;

    @Column(name="country_population")
    private long countryPopulation;

    @Column(name="country_flag_url")
    private String countryFlagUrl;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCapital() {
        return countryCapital;
    }

    public void setCountryCapital(String countryCapital) {
        this.countryCapital = countryCapital;
    }

    public long getCountryPopulation() {
        return countryPopulation;
    }

    public void setCountryPopulation(long countryPopulation) {
        this.countryPopulation = countryPopulation;
    }

    public String getCountryFlagUrl() {
        return countryFlagUrl;
    }

    public void setCountryFlagUrl(String countryFlagUrl) {
        this.countryFlagUrl = countryFlagUrl;
    }
}
