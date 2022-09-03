package com.nordea.assigment.countryservice.model.dto;

import com.nordea.assigment.countryservice.model.ouputs.IResult;

public class CountryBriefDTO implements IResult {
    private String name;
    private String country_code;

    public CountryBriefDTO(String name, String country_code) {
        this.name = name;
        this.country_code = country_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }
}
