package com.nordea.assigment.countryservice.service;

import com.nordea.assigment.countryservice.model.events.CountryServiceEvent;
import com.nordea.assigment.countryservice.model.ouputs.OperationOutput;
import com.nordea.assigment.countryservice.model.ouputs.OutputType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CountryServiceEventListenerImpl implements ApplicationListener<CountryServiceEvent> {

    final CountryServiceLogger loggerService;
    final CountryServiceCordinator countryServiceCordinator;

    @Autowired
    public CountryServiceEventListenerImpl(CountryServiceLogger loggerService,CountryServiceCordinator countryServiceCordinator){
        this.loggerService = loggerService;
        this.countryServiceCordinator = countryServiceCordinator;
    }

    @Override
    public void onApplicationEvent(CountryServiceEvent event) {
        loggerService.logAnOutput(new OperationOutput(OutputType.SUCCESS,
                                              "Someone need countries data, Let's gather it again")
                                 ,this.getClass());
        countryServiceCordinator.fetchCountriesDataAndStore();

    }
}
