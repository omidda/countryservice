package com.nordea.assigment.countryservice.service;

import com.nordea.assigment.countryservice.model.dto.JsonDTO;
import com.nordea.assigment.countryservice.model.ouputs.OperationOutput;
import com.nordea.assigment.countryservice.model.ouputs.OutputType;
import com.nordea.assigment.countryservice.model.ouputs.SingleOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Service
@Scope("singleton")
public class CountriesDataGatherImpl implements CountriesDataGather {

    private final CountryServiceLogger loggerService;

    @Autowired
    public CountriesDataGatherImpl(CountryServiceLogger loggerService) {
        this.loggerService = loggerService;
    }
    private final static String countriesRestUrl = "https://restcountries.com/v3.1/all";
    private final static String countriesRestHttpMethod = "GET";
    private HttpURLConnection httpConnectionToRestSourse;


    @Override
    public SingleOutput fetchCountriesDataAsJsonString() {
        SingleOutput output;

        OperationOutput connectionResult = connectToRestResource();

        if(connectionResult.isOperationSuccessful())
        {
            output = readStreamAndFetchJsonData();

            disconnectFromRestResource();

        } else {
            output = new SingleOutput(connectionResult);
        }

        return output;
    }

    private OperationOutput connectToRestResource(){

        OperationOutput output;

        try {

            URL countriesRestUrlObject = new URL(countriesRestUrl);

            httpConnectionToRestSourse = (HttpURLConnection) countriesRestUrlObject.openConnection();
            httpConnectionToRestSourse.setRequestMethod(countriesRestHttpMethod);
            httpConnectionToRestSourse.connect();

            int httpResponceCode = httpConnectionToRestSourse.getResponseCode();
            if(httpResponceCode == 200)
            {
                output = new OperationOutput(OutputType.SUCCESS);
            }
            else {
                output = new OperationOutput(OutputType.ERROR, "Resource is not available, response code: " + httpResponceCode);
                loggerService.logAnOutput(output,this.getClass());
            }

        }
        catch (IOException exception){
            output = new OperationOutput(OutputType.ERROR,"Connection to " + countriesRestUrl + " is not available");
            loggerService.logOutputAndException(output, exception ,this.getClass());
        }
        return output;
    }

    SingleOutput readStreamAndFetchJsonData() {

        SingleOutput output;

        try {

            StringBuilder jsonAsString = new StringBuilder();
            Scanner scanner = new Scanner(httpConnectionToRestSourse.getInputStream());

            while (scanner.hasNext()) {
                jsonAsString.append(scanner.nextLine());
            }

            scanner.close();

            output = new SingleOutput(OutputType.SUCCESS, new JsonDTO(jsonAsString.toString()));

        } catch (IOException exception) {
            output = new SingleOutput(OutputType.ERROR,"Received data is not readable");
            loggerService.logOutputAndException(output, exception ,this.getClass());
        }

        return output;

    }

    private OperationOutput disconnectFromRestResource() {

        OperationOutput output;

        try {
            httpConnectionToRestSourse.disconnect();
            output = new OperationOutput(OutputType.SUCCESS);
        } catch (Exception exception) {
            output = new OperationOutput(OutputType.ERROR,
                    "connection to Rest source could not be disconnected");
            loggerService.logOutputAndException(output, exception ,this.getClass());
        }

        return output;
    }





}
