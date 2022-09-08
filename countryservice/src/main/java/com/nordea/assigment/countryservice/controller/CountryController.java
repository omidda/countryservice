package com.nordea.assigment.countryservice.controller;

import com.nordea.assigment.countryservice.model.ouputs.CountryListRestDTO;
import com.nordea.assigment.countryservice.model.ouputs.ListOutput;
import com.nordea.assigment.countryservice.model.ouputs.SingleOutput;
import com.nordea.assigment.countryservice.service.CountriesDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// This annotation is for enabling front-end layer connection to back-end Rest services.
// Without CrossOrigin policy browser prevent making request in localhost.
// It can be limited to a specific domain such as *.nordea.com
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CountryController {

    final CountriesDataStore countriesDataStore;

    @Autowired
    public  CountryController(CountriesDataStore countriesDataStore){
        this.countriesDataStore = countriesDataStore;
    }

    @GetMapping("/countries")
    Object fetchingCountries(){

        ListOutput countriesOutput =  countriesDataStore.findAllCountries();

        if(countriesOutput.isOperationSuccessful())
            return new CountryListRestDTO(countriesOutput.getResults());
        else
            return countriesOutput;

    }

    @GetMapping("/countries/{countryName}")
    Object fetchingCountries(@PathVariable String countryName){
        SingleOutput countryOutput = countriesDataStore.findCountryByName(countryName);
        if(countryOutput.isOperationSuccessful())
            return countryOutput.getResult();
        else
            return countryOutput;
    }


}
