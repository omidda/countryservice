package com.nordea.assigment.countryservice.service;

import com.nordea.assigment.countryservice.model.ouputs.SingleOutput;

public interface CountriesDataGather {
    SingleOutput fetchCountriesDataAsJsonString();
}
