package com.nordea.assigment.countryservice.model.ouputs;

import java.util.List;

public class CountryListRestDTO {

    List<IResult> countries;

    public CountryListRestDTO(List<IResult> countries){
        this.countries = countries;
    }

    public List<IResult> getCountries() {
        return countries;
    }

    public void setCountries(List<IResult> countries) {
        this.countries = countries;
    }
}
