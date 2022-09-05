package com.nordea.assigment.countryservice;

import com.nordea.assigment.countryservice.model.dto.CountryDTO;
import com.nordea.assigment.countryservice.model.dto.JsonDTO;
import com.nordea.assigment.countryservice.model.ouputs.ListOutput;
import com.nordea.assigment.countryservice.model.ouputs.OperationOutput;
import com.nordea.assigment.countryservice.model.ouputs.OutputType;
import com.nordea.assigment.countryservice.model.ouputs.SingleOutput;
import com.nordea.assigment.countryservice.service.CountriesDataGather;
import com.nordea.assigment.countryservice.service.CountriesDataStore;
import com.nordea.assigment.countryservice.service.JsonProcessor;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DataLayerTests {

	@Autowired
	CountriesDataStore countriesDataStore;


	public List<CountryDTO> setUpListToInsert() {

		List<CountryDTO> countryDTOSToInsert = new ArrayList<>();

		CountryDTO finland = new CountryDTO();

		finland.setPopulation(125000L);
		finland.setCapital("Helsinki");
		finland.setFlag_file_url("https://finlandflag.png");
		finland.setName("Finland");
		finland.setCountry_code("FI");

		countryDTOSToInsert.add(finland);

		CountryDTO estonia = new CountryDTO();

		estonia.setPopulation(1255200L);
		estonia.setCapital("Tallinn");
		estonia.setFlag_file_url("https://estoniaflag.png");
		estonia.setName("Estonia");
		estonia.setCountry_code("EE");

		countryDTOSToInsert.add(estonia);

		return countryDTOSToInsert;

	}

	public ListOutput insertSeveralCountries() {
		return countriesDataStore.insertCountries(setUpListToInsert());
	}

	public ListOutput fetchingSeveralCountries() {
		return countriesDataStore.findAllCountries();
	}

	public SingleOutput fetchingSingleCountry() {
		return countriesDataStore.findCountryByName("Estonia");
	}

	@Test
	public void assertInsertSeveralCountries() {

		ListOutput result = insertSeveralCountries();

		assertThat(result.getType()).isEqualTo(OutputType.SUCCESS);
		assertThat(result.getResults().size()).isEqualTo(2);
		assertThat(((CountryDTO)result.getResults().get(0)).getName()).isEqualTo("Finland");

	}

	@Test
	public void assertFetchingSeveralCountries() {

		insertSeveralCountries();

		ListOutput result = fetchingSeveralCountries();

		assertThat(result.getType()).isEqualTo(OutputType.SUCCESS);
		assertThat(result.getResults().size()).isEqualTo(2);
		assertThat(((CountryDTO)result.getResults().get(0)).getName()).isEqualTo("Finland");

	}


	@Test
	public void assertFetchingOneCountry() {

		insertSeveralCountries();

		SingleOutput result = fetchingSingleCountry();

		assertThat(result.getType()).isEqualTo(OutputType.SUCCESS);
		assertThat(result.getResult()).isNotNull();
		assertThat(((CountryDTO)result.getResult()).getName()).isEqualTo("Estonia");

	}

}
