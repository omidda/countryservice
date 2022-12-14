

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

### Rest APIs
GET /countries/
```
response:
          {
                   "countries": [
                             {
                                       "name": "Finland",
                                       "country_code": "FI"
                             },
                             ...
                   ]
          }
```
 

GET /countries/{name}

response:

          {

                   "name": "Finland",

                   "country_code": "FI",

                   "capital": "Helsinki",

                   "population": 5491817,

                   "flag_file_url": "<url to the flag file>"

          }
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

### Testing
Some unit test is written in the test folder. 
 - **DataLayerTests** : Contains test cases related to the data layer for inserting and fetching data.
 - **FetchingAndProcessingCountriesDataTests** : Testing fetching and processing JSON data related services

> We can add integration or end-to-end test, as well as different test cases to reach a better test coverage.

## Front-end 
To run this simple Front-end application you just need a browser that can run JavaScript code. Do not worry most modern browsers do it. Just open **index.html** and type the country's name in the text box.

> You can define the address of the country service Rest API address by **COUNTRY_SERVICE_API_URL** constant in **main.js**

![enter image description here](https://i.postimg.cc/d3wP9FHt/1.png)

![enter image description here](https://i.postimg.cc/QtP0qZXK/2.png)


