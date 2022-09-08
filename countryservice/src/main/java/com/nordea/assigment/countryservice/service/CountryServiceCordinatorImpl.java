package com.nordea.assigment.countryservice.service;

import com.nordea.assigment.countryservice.model.dto.JsonDTO;
import com.nordea.assigment.countryservice.model.ouputs.ListOutput;
import com.nordea.assigment.countryservice.model.ouputs.OperationOutput;
import com.nordea.assigment.countryservice.model.ouputs.OutputType;
import com.nordea.assigment.countryservice.model.ouputs.SingleOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceCordinatorImpl implements CountryServiceCordinator {

    final CountriesDataGather countriesDataGather;
    final CountriesDataStore countriesDataStore;
    final JsonProcessor jsonProcessor;
    final CountryServiceLogger countryServiceLogger;

    @Autowired
    public CountryServiceCordinatorImpl(CountriesDataGather countriesDataGather,
                                        CountriesDataStore countriesDataStore,
                                        JsonProcessor jsonProcessor,
                                        CountryServiceLogger countryServiceLogger) {

        this.countriesDataStore = countriesDataStore;
        this.countriesDataGather = countriesDataGather;
        this.jsonProcessor = jsonProcessor;
        this.countryServiceLogger = countryServiceLogger;

        fetchCountriesDataAndStore();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
            fetchCountriesDataAndStore();
    }

    @Override
    public void fetchCountriesDataAndStore(){

        SingleOutput countriesDataGatheringResult = countriesDataGather.fetchCountriesDataAsJsonString();

        if(countriesDataGatheringResult.isOperationSuccessful()) {

            countryServiceLogger.logAnOutput(new OperationOutput(OutputType.SUCCESS,"Countries data gathered successfully."),
                                             this.getClass());

            ListOutput jsonToCountriesConvertionResult = jsonProcessor.processJsonStringAndConvertToCountriesList((JsonDTO) countriesDataGatheringResult.getResult());

            if(jsonToCountriesConvertionResult.isOperationSuccessful()) {

                ListOutput countriesStoringResult = countriesDataStore.insertCountries((List)jsonToCountriesConvertionResult.getResults());

                if(countriesStoringResult.isOperationSuccessful()) {

                    countryServiceLogger.logAnOutput(new OperationOutput(OutputType.SUCCESS,"Countries data store in database successfully."),
                            this.getClass());
                    return;

                }

            }

        }

        countryServiceLogger.logAnOutput(new OperationOutput(OutputType.ERROR,"Something went wrong on data manipulation, Country service try again"),
                this.getClass());

    }


}
