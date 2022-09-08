

# Country Service Documentation
After reading this document you will be able to set up the development environment and run the project.
The repository consists of two folders:

 - [countryservice](https://github.com/omidda/countryservice/tree/main/countryservice "countryservice") : contain Java codes for back-end side
 - [countryservice-front](https://github.com/omidda/countryservice/tree/main/countryservice-front "countryservice-front") : contain HTML, JavaScript codes for front-end side.

## Pre-requirement 
- **Maven**   - https://maven.apache.org/install.html
- **Java 17** - https://www.oracle.com/java/technologies/downloads/#java17
- **Environment Variables** : This variable should be defined in your OS, Cloud provider dashboard or in your IDE before running the project. 
>COUNTRY_SERVICE_LOG_PATH : Indicate logs' path

>COUNTRY_SERVICE_HTTP_PORT : Indicate micro-service's HTTP port

>these environment variables make a straightforward way to containerize the micro-service

## Back-end side

>When the Spring container starts the micro-service try to fetch countries' data once and store it on the H2 in-memory database, Therefore for each request the micro-service does not gather data from the remote source. If the micro-service received a request for countries' data and the database was empty because of network failure and so on, an event initiate to re-fetch data again.

**Maven dependencies**:
 - spring-boot-starter-web : For providing HTTP Rest APIs
 - spring-boot-starter-log4j2 : For logging system. 
 - spring-boot-starter-test: For test purposes
 - json-simple: A library for praseing JSON data.
 - spring-boot-starter-data-jpa: Spring JPA for storing data and working with database
 - h2: Is an embedded in-memory database for storing countries' data

### Logging system
The application events are logged on a file-size base rotating policy and this policy can be changed. the logs will be stored on the path which is defined in COUNTRY_SERVICE_LOG_PATH  [Read more about log4j configuration.](https://logging.apache.org/log4j/2.x/manual/configuration.html) 

### Services
 - **JsonProcessor** : Recieve JSON data as a string and convert it to a java object [ CountryDTO ]
 - **CountryServiceLogger** : Getting general Output classes and log error, exception or important event
 - **CountryServiceEventListenerImpl** : An event listener for re-fetching counries' data from remote source
 - **CountryServiceCordinator** : Act as a coordinator and utilize all capabilities of other services like JsonProcessor, CountryServiceLogger, and CountriesDataStore to gather, convert and store countries' data.
 - **CountriesDataStore** : Implement the data layer. its responsibility is storing, fetching, and converting data.

 ### Data Layer
 - **CountryRepository** : A repository for database operations which use **CountryEntity** to persist fetched data.

## Front-end 
....
