package com.nordea.assigment.countryservice.service;

import com.nordea.assigment.countryservice.model.dto.CountryDTO;
import com.nordea.assigment.countryservice.model.ouputs.ListOutput;
import com.nordea.assigment.countryservice.model.ouputs.OperationOutput;
import com.nordea.assigment.countryservice.model.ouputs.SingleOutput;

import java.util.List;

public interface CountriesDataStore {
    ListOutput insertCountries(List<CountryDTO> inputDTOs);

    ListOutput findAllCountries();

    SingleOutput findCountryByName(String countryName);
}
