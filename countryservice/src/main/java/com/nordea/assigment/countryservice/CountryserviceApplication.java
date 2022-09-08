package com.nordea.assigment.countryservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CountryserviceApplication {


	private static final Logger logger = LogManager.getLogger(CountryserviceApplication.class);

	public static void main(String[] args) {

		checkLoggingEnvironmentVariableAndSetInternalProperties();

		SpringApplication.run(CountryserviceApplication.class, args);

	}


	static void checkLoggingEnvironmentVariableAndSetInternalProperties(){
		if(!System.getenv().containsKey("COUNTRY_SERVICE_LOG_PATH") || System.getenv("COUNTRY_SERVICE_LOG_PATH").isEmpty())
		{
			logger.error("COUNTRY_SERVICE_LOG_PATH is not defined, please set this environment variable to store country service logs.");
		}
	}

}
