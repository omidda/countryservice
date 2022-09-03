package com.nordea.assigment.countryservice;

import com.nordea.assigment.countryservice.model.dto.JsonDTO;
import com.nordea.assigment.countryservice.model.ouputs.OutputType;
import com.nordea.assigment.countryservice.model.ouputs.SingleOutput;
import com.nordea.assigment.countryservice.service.CountriesDataGather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CountriesDataGatheringFromRestSourceTests {

	@Autowired
	CountriesDataGather countriesDataGather;

	@Test
	public void contextLoads() {
		SingleOutput result = countriesDataGather.fetchCountriesDataAsJsonString();
		assertThat(result.getType()).isEqualTo(OutputType.SUCCESS);
		assertThat(((JsonDTO)result.getResult()).getJson()).isNotEmpty().contains("{");
	}

}
