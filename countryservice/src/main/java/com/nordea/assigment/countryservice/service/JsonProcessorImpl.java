package com.nordea.assigment.countryservice.service;

import com.nordea.assigment.countryservice.model.dto.CountryDTO;
import com.nordea.assigment.countryservice.model.ouputs.ListOutput;
import com.nordea.assigment.countryservice.model.ouputs.OutputType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("singleton")
public class JsonProcessorImpl  implements JsonProcessor{

    private final static JSONParser jsonParser = new JSONParser();

    private final CountryServiceLogger loggerService;

    @Autowired
    public JsonProcessorImpl(CountryServiceLogger loggerService) {
        this.loggerService = loggerService;
    }

    @Override
    public ListOutput processJsonStringAndConvertToCountriesList(String inputJson){

        ListOutput countriesOutput;

        try {

            JSONArray jsonArrayOfCountries = (JSONArray) jsonParser.parse(inputJson);
            List countryDTOS = convertJsonArrayToCountryDTOList(jsonArrayOfCountries);

            if(countryDTOS != null && countryDTOS.size() > 0)
            {
                countriesOutput = new ListOutput(OutputType.SUCCESS,countryDTOS);
            }
            else {
                countriesOutput = new ListOutput(OutputType.ERROR,"Error in processing and converting JSON data");
            }

        } catch (ParseException exception) {
            countriesOutput = new ListOutput(OutputType.ERROR,"Invalid JSON for conversions");
            loggerService.logAnOutput(countriesOutput,this.getClass());
            loggerService.logAnException(exception,this.getClass());
        }

        return countriesOutput;
    }

    private List<CountryDTO> convertJsonArrayToCountryDTOList(JSONArray countryJsonArray){

        List<CountryDTO> outputCountries = new ArrayList<>();

        countryJsonArray.stream().forEach(jsonObject -> {
            CountryDTO convertedJsonObjectToCountryDTO = convertJsonObjectToCountryDTO((JSONObject) jsonObject);
            if(convertedJsonObjectToCountryDTO != null)
                outputCountries.add(convertedJsonObjectToCountryDTO);
        });

        return outputCountries;

    }

    private CountryDTO convertJsonObjectToCountryDTO(JSONObject countryJsonObject) {

        CountryDTO outputCountry = new CountryDTO();
        try {

            outputCountry.setCountry_code(countryJsonObject.get("cca2").toString());
            outputCountry.setPopulation((Long) countryJsonObject.get("population"));

            JSONObject nameOfCountryJsonObject = (JSONObject) countryJsonObject.get("name");
            outputCountry.setName(nameOfCountryJsonObject.get("common").toString());

            JSONArray capitalArrayOfCountryJsonObject = (JSONArray) countryJsonObject.get("capital");
            outputCountry.setCapital((String) capitalArrayOfCountryJsonObject.get(0));

            JSONObject flagsOfCountryJsonObject = (JSONObject) countryJsonObject.get("flags");
            outputCountry.setFlag_file_url(flagsOfCountryJsonObject.get("png").toString());
        }
        catch (Exception exception){
            loggerService.logAnException(exception,this.getClass());
        }

        return outputCountry;
    }
}
