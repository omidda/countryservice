package com.nordea.assigment.countryservice;

import com.nordea.assigment.countryservice.model.dto.CountryDTO;
import com.nordea.assigment.countryservice.model.dto.JsonDTO;
import com.nordea.assigment.countryservice.model.ouputs.ListOutput;
import com.nordea.assigment.countryservice.model.ouputs.OutputType;
import com.nordea.assigment.countryservice.model.ouputs.SingleOutput;
import com.nordea.assigment.countryservice.service.CountriesDataGather;
import com.nordea.assigment.countryservice.service.JsonProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FetchingAndProcessingCountriesDataTests {

	@Autowired
	CountriesDataGather countriesDataGather;

	@Autowired
	JsonProcessor jsonProcessor;

	@Test
	public void testFetchingDataFromRestSource() {
		SingleOutput result = countriesDataGather.fetchCountriesDataAsJsonString();
		assertThat(result.getType()).isEqualTo(OutputType.SUCCESS);
		assertThat(((JsonDTO)result.getResult()).getJson()).isNotEmpty().contains("{");
	}

	@Test
	public void testJsonProcessing() {

		String jsonString = "[{\n" +
				"        \"name\": {\n" +
				"            \"common\": \"Finland\",\n" +
				"            \"official\": \"Republic of Finland\",\n" +
				"            \"nativeName\": {\n" +
				"                \"fin\": {\n" +
				"                    \"official\": \"Suomen tasavalta\",\n" +
				"                    \"common\": \"Suomi\"\n" +
				"                },\n" +
				"                \"swe\": {\n" +
				"                    \"official\": \"Republiken Finland\",\n" +
				"                    \"common\": \"Finland\"\n" +
				"                }\n" +
				"            }\n" +
				"        },\n" +
				"        \"tld\": [\n" +
				"            \".fi\"\n" +
				"        ],\n" +
				"        \"cca2\": \"FI\",\n" +
				"        \"ccn3\": \"246\",\n" +
				"        \"cca3\": \"FIN\",\n" +
				"        \"cioc\": \"FIN\",\n" +
				"        \"independent\": true,\n" +
				"        \"status\": \"officially-assigned\",\n" +
				"        \"unMember\": true,\n" +
				"        \"currencies\": {\n" +
				"            \"EUR\": {\n" +
				"                \"name\": \"Euro\",\n" +
				"                \"symbol\": \"???\"\n" +
				"            }\n" +
				"        },\n" +
				"        \"idd\": {\n" +
				"            \"root\": \"+3\",\n" +
				"            \"suffixes\": [\n" +
				"                \"58\"\n" +
				"            ]\n" +
				"        },\n" +
				"        \"capital\": [\n" +
				"            \"Helsinki\"\n" +
				"        ],\n" +
				"        \"altSpellings\": [\n" +
				"            \"FI\",\n" +
				"            \"Suomi\",\n" +
				"            \"Republic of Finland\",\n" +
				"            \"Suomen tasavalta\",\n" +
				"            \"Republiken Finland\"\n" +
				"        ],\n" +
				"        \"region\": \"Europe\",\n" +
				"        \"subregion\": \"Northern Europe\",\n" +
				"        \"languages\": {\n" +
				"            \"fin\": \"Finnish\",\n" +
				"            \"swe\": \"Swedish\"\n" +
				"        },\n" +
				"        \"translations\": {\n" +
				"            \"ara\": {\n" +
				"                \"official\": \"?????????????? ????????????\",\n" +
				"                \"common\": \"????????????\"\n" +
				"            },\n" +
				"            \"bre\": {\n" +
				"                \"official\": \"Republik Finland\",\n" +
				"                \"common\": \"Finland\"\n" +
				"            },\n" +
				"            \"ces\": {\n" +
				"                \"official\": \"Finsk?? republika\",\n" +
				"                \"common\": \"Finsko\"\n" +
				"            },\n" +
				"            \"cym\": {\n" +
				"                \"official\": \"Republic of Finland\",\n" +
				"                \"common\": \"Finland\"\n" +
				"            },\n" +
				"            \"deu\": {\n" +
				"                \"official\": \"Republik Finnland\",\n" +
				"                \"common\": \"Finnland\"\n" +
				"            },\n" +
				"            \"est\": {\n" +
				"                \"official\": \"Soome Vabariik\",\n" +
				"                \"common\": \"Soome\"\n" +
				"            },\n" +
				"            \"fin\": {\n" +
				"                \"official\": \"Suomen tasavalta\",\n" +
				"                \"common\": \"Suomi\"\n" +
				"            },\n" +
				"            \"fra\": {\n" +
				"                \"official\": \"R??publique de Finlande\",\n" +
				"                \"common\": \"Finlande\"\n" +
				"            },\n" +
				"            \"hrv\": {\n" +
				"                \"official\": \"Republika Finska\",\n" +
				"                \"common\": \"Finska\"\n" +
				"            },\n" +
				"            \"hun\": {\n" +
				"                \"official\": \"Finn K??zt??rsas??g\",\n" +
				"                \"common\": \"Finnorsz??g\"\n" +
				"            },\n" +
				"            \"ita\": {\n" +
				"                \"official\": \"Repubblica di Finlandia\",\n" +
				"                \"common\": \"Finlandia\"\n" +
				"            },\n" +
				"            \"jpn\": {\n" +
				"                \"official\": \"???????????????????????????\",\n" +
				"                \"common\": \"??????????????????\"\n" +
				"            },\n" +
				"            \"kor\": {\n" +
				"                \"official\": \"????????? ?????????\",\n" +
				"                \"common\": \"?????????\"\n" +
				"            },\n" +
				"            \"nld\": {\n" +
				"                \"official\": \"Republiek Finland\",\n" +
				"                \"common\": \"Finland\"\n" +
				"            },\n" +
				"            \"per\": {\n" +
				"                \"official\": \"???????????? ????????????\",\n" +
				"                \"common\": \"????????????\"\n" +
				"            },\n" +
				"            \"pol\": {\n" +
				"                \"official\": \"Republika Finlandii\",\n" +
				"                \"common\": \"Finlandia\"\n" +
				"            },\n" +
				"            \"por\": {\n" +
				"                \"official\": \"Rep??blica da Finl??ndia\",\n" +
				"                \"common\": \"Finl??ndia\"\n" +
				"            },\n" +
				"            \"rus\": {\n" +
				"                \"official\": \"?????????????????????? ????????????????????\",\n" +
				"                \"common\": \"??????????????????\"\n" +
				"            },\n" +
				"            \"slk\": {\n" +
				"                \"official\": \"F??nska republika\",\n" +
				"                \"common\": \"F??nsko\"\n" +
				"            },\n" +
				"            \"spa\": {\n" +
				"                \"official\": \"Rep??blica de Finlandia\",\n" +
				"                \"common\": \"Finlandia\"\n" +
				"            },\n" +
				"            \"swe\": {\n" +
				"                \"official\": \"Republiken Finland\",\n" +
				"                \"common\": \"Finland\"\n" +
				"            },\n" +
				"            \"urd\": {\n" +
				"                \"official\": \"?????????????? ???? ????????\",\n" +
				"                \"common\": \"???? ????????\"\n" +
				"            },\n" +
				"            \"zho\": {\n" +
				"                \"official\": \"???????????????\",\n" +
				"                \"common\": \"??????\"\n" +
				"            }\n" +
				"        },\n" +
				"        \"latlng\": [\n" +
				"            64.0,\n" +
				"            26.0\n" +
				"        ],\n" +
				"        \"landlocked\": false,\n" +
				"        \"borders\": [\n" +
				"            \"NOR\",\n" +
				"            \"SWE\",\n" +
				"            \"RUS\"\n" +
				"        ],\n" +
				"        \"area\": 338424.0,\n" +
				"        \"demonyms\": {\n" +
				"            \"eng\": {\n" +
				"                \"f\": \"Finnish\",\n" +
				"                \"m\": \"Finnish\"\n" +
				"            },\n" +
				"            \"fra\": {\n" +
				"                \"f\": \"Finlandaise\",\n" +
				"                \"m\": \"Finlandais\"\n" +
				"            }\n" +
				"        },\n" +
				"        \"flag\": \"\uD83C\uDDEB\uD83C\uDDEE\",\n" +
				"        \"maps\": {\n" +
				"            \"googleMaps\": \"https://goo.gl/maps/HjgWDCNKRAYHrkMn8\",\n" +
				"            \"openStreetMaps\": \"openstreetmap.org/relation/54224\"\n" +
				"        },\n" +
				"        \"population\": 5530719,\n" +
				"        \"gini\": {\n" +
				"            \"2018\": 27.3\n" +
				"        },\n" +
				"        \"fifa\": \"FIN\",\n" +
				"        \"car\": {\n" +
				"            \"signs\": [\n" +
				"                \"FIN\"\n" +
				"            ],\n" +
				"            \"side\": \"right\"\n" +
				"        },\n" +
				"        \"timezones\": [\n" +
				"            \"UTC+02:00\"\n" +
				"        ],\n" +
				"        \"continents\": [\n" +
				"            \"Europe\"\n" +
				"        ],\n" +
				"        \"flags\": {\n" +
				"            \"png\": \"https://flagcdn.com/w320/fi.png\",\n" +
				"            \"svg\": \"https://flagcdn.com/fi.svg\"\n" +
				"        },\n" +
				"        \"coatOfArms\": {\n" +
				"            \"png\": \"https://mainfacts.com/media/images/coats_of_arms/fi.png\",\n" +
				"            \"svg\": \"https://mainfacts.com/media/images/coats_of_arms/fi.svg\"\n" +
				"        },\n" +
				"        \"startOfWeek\": \"monday\",\n" +
				"        \"capitalInfo\": {\n" +
				"            \"latlng\": [\n" +
				"                60.17,\n" +
				"                24.93\n" +
				"            ]\n" +
				"        },\n" +
				"        \"postalCode\": {\n" +
				"            \"format\": \"#####\",\n" +
				"            \"regex\": \"^(?:FI)*(\\\\d{5})$\"\n" +
				"        }\n" +
				"    }]";

		ListOutput processedCountriesList = jsonProcessor.processJsonStringAndConvertToCountriesList(new JsonDTO(jsonString));

		assertThat(processedCountriesList).isNotNull();
		assertThat(((CountryDTO)processedCountriesList.getResults().get(0)).getName()).isEqualTo("Finland");
	}

}
