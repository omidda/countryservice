package com.nordea.assigment.countryservice.service;

import com.nordea.assigment.countryservice.model.dto.JsonDTO;
import com.nordea.assigment.countryservice.model.ouputs.ListOutput;

public interface JsonProcessor {

    ListOutput processJsonStringAndConvertToCountriesList(JsonDTO inputJson);
}
