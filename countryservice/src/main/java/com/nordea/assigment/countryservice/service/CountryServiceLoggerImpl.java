package com.nordea.assigment.countryservice.service;

import com.nordea.assigment.countryservice.model.ouputs.Output;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class CountryServiceLoggerImpl implements CountryServiceLogger {

    private static final Logger logger = LogManager.getLogger("CountryServiceLogger");

    @Override
    public boolean logOutputAndException(Output outputForLog,
                                      Exception exceptionForLog,
                                      Class invokerClass){

        try {
            if (outputForLog != null && outputForLog.getType() != null) {
                switch (outputForLog.getType()) {
                    case SUCCESS -> logSuccess(outputForLog, invokerClass);
                    case ERROR -> logError(outputForLog, invokerClass);
                }
                ;
            }

            logAnException(exceptionForLog, invokerClass);
            return true;
        }
        catch (Exception exception){
            return false;
        }
    }

    @Override
    public boolean logAnOutput(Output outputForLog, Class invokerClass) {
        try {
            if (outputForLog != null && outputForLog.getType() != null) {
                switch (outputForLog.getType()) {
                    case SUCCESS -> logSuccess(outputForLog, invokerClass);
                    case ERROR -> logError(outputForLog, invokerClass);
                }
            }
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public boolean logAnException(Exception exceptionForLog, Class invokerClass){
        try {
            logger.error(invokerClass.getPackageName() + " " + invokerClass.getName() + "->");
            logger.error(exceptionForLog);
            return true;
        }catch (Exception exception)
        {
            return false;
        }
    }

    void logError(Output outpuForLog,Class invokerClass){
        logger.error(invokerClass.getPackageName() + " " + invokerClass.getName() + "->" + outpuForLog.getMessage());
    }
    void logSuccess(Output outpuForLog,Class invokerClass){
        logger.info(invokerClass.getPackageName() + " " + invokerClass.getName() + "->" + outpuForLog.getMessage());
    }


}
