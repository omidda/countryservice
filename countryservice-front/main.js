
const COUNTRY_SERVICE_API_URL = 'http://localhost:8090/'



function fetchAllCountriesData(){


    fetchDataFromRestAPI(COUNTRY_SERVICE_API_URL+'countries')
    .then((response) => response.json())
    .then((result) => { 

        var countries = generateCountriesArray(result.countries);
        configureAutoComplete(countries);

     }).catch((error)=>{
        return ["An error occured during fetching countries"]
     });
}

function fetchCountryDetail(countryName){

    fetchDataFromRestAPI(COUNTRY_SERVICE_API_URL + 'countries' + '/' + countryName)
    .then((response) => response.json())
    .then((result) => { 
        visualCountryDetail(result);
     }).catch((error)=>{
        return ["An error occured during fetching countries"]
     });
}

function configureAutoComplete(countries) {
    $(function () {
        $("#countries").autocomplete({
            source: countries,
            select: function (event, ui) {
                fetchCountryDetail(ui.item.value);
            }
        });
    });
}

async function fetchDataFromRestAPI(URL){
    return fetch(URL)
}

function generateCountriesArray(inputCountries){
    let outputArray = []
    inputCountries.map((country)=>{
        outputArray.push(country.name)
    })
    return outputArray;
}
function visualCountryDetail(countryDetail){
    $("#countryName").html(countryDetail.name);
    $("#countryCode").html(countryDetail.country_code);
    $("#countryCapital").html(countryDetail.capital);
    $("#countryPopulation").html(countryDetail.population);
    $("#countryImage").attr("src",countryDetail.flag_file_url);
}
