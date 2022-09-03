package com.nordea.assigment.countryservice.model.dto;

import com.nordea.assigment.countryservice.model.ouputs.IResult;

public class CountryDTO extends CountryBriefDTO {

    private String capital;
    private Long population;
    private String flag_file_url;

    public CountryDTO(){
        super();
    }

    public CountryDTO(String name, String country_code, String capital, Long population, String flag_file_url) {
        super(name, country_code);
        this.capital = capital;
        this.population = population;
        this.flag_file_url = flag_file_url;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public String getFlag_file_url() {
        return flag_file_url;
    }

    public void setFlag_file_url(String flag_file_url) {
        this.flag_file_url = flag_file_url;
    }
}
