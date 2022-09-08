package com.nordea.assigment.countryservice.model.dto;

public class CountryDTO extends CountryBriefDTO {

    private String capital;
    private Long population;
    private String flag_file_url;

    public CountryDTO(){
        super();
    }

    public CountryDTO(CountryBriefDTO countryBriefDTO){
        super(countryBriefDTO);
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
