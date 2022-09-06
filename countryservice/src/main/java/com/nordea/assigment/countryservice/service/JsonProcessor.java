package com.nordea.assigment.countryservice.service;

import com.nordea.assigment.countryservice.model.dto.CountryDTO;
import com.nordea.assigment.countryservice.model.dto.JsonDTO;
import com.nordea.assigment.countryservice.model.ouputs.ListOutput;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public interface JsonProcessor {

    ListOutput processJsonStringAndConvertToCountriesList(JsonDTO inputJson);
}
