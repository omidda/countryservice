package com.nordea.assigment.countryservice.service;

import com.nordea.assigment.countryservice.model.ouputs.Output;

public interface CountryServiceLogger {
    boolean logOutputAndException(Output outputForLog,
                               Exception exceptionForLog,
                               Class invokerClass);

    boolean logAnOutput(Output outputForLog, Class T);

    boolean logAnException(Exception exceptionForLog, Class invokerClass);
}
