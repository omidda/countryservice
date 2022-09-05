package com.nordea.assigment.countryservice.repository;

import com.nordea.assigment.countryservice.model.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity,Long> {

    CountryEntity findByCountryName(String countryName);

}
