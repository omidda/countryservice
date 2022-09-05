package com.nordea.assigment.countryservice.service;

import com.nordea.assigment.countryservice.model.dto.CountryBriefDTO;
import com.nordea.assigment.countryservice.model.dto.CountryDTO;
import com.nordea.assigment.countryservice.model.entity.CountryEntity;
import com.nordea.assigment.countryservice.model.ouputs.ListOutput;
import com.nordea.assigment.countryservice.model.ouputs.OperationOutput;
import com.nordea.assigment.countryservice.model.ouputs.OutputType;
import com.nordea.assigment.countryservice.model.ouputs.SingleOutput;
import com.nordea.assigment.countryservice.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


// CountriesDataStoreImpl use to store and fetching countries' data by embedded H2 database

@Service
public class CountriesDataStoreImpl implements CountriesDataStore {

    CountryRepository countryRepository;
    CountryServiceLogger loggerService;

    @Autowired
    public CountriesDataStoreImpl(CountryRepository countryRepository,
                                  CountryServiceLogger countryServiceLogger){

        this.countryRepository = countryRepository;
        this.loggerService = countryServiceLogger;

    }

    @Override
    public ListOutput insertCountries(List<CountryDTO> inputDTOs){

        ListOutput output;

        List<CountryEntity> entitiesToInsert = convertDTOsToEntities(inputDTOs);

        if(entitiesToInsert == null || entitiesToInsert.size() < 1) {
            return new ListOutput(OutputType.ERROR,
                    "Nothing to save");
        }

        List<CountryEntity> insertedEntities = countryRepository.saveAll(entitiesToInsert);

        if(insertedEntities != null && insertedEntities.size() > 0){
            output = new ListOutput(OutputType.SUCCESS, (List) inputDTOs);
        }
        else {
            output = new ListOutput(OutputType.ERROR,
                    "An error occured while persisting countries");
        }

        return output;
    }

    @Override
    public ListOutput findAllCountries(){

        ListOutput output;

        try {
            List<CountryEntity> retrievedCountries =
                    countryRepository.findAll();

            if (retrievedCountries != null &&
                    retrievedCountries.size() > 0) {

                List<CountryBriefDTO> outputDTOs = convertCountryEntityToBriefDTOList(retrievedCountries);

                output = new ListOutput(OutputType.SUCCESS, (List) outputDTOs);

            } else {

                output = new ListOutput(OutputType.SUCCESS, "Countries data not found");

            }
        }
        catch (Exception exception){
            output = new ListOutput(OutputType.ERROR,"An error occured while fetching countries list.");
            loggerService.logOutputAndException(output, exception ,this.getClass());
        }

        return output;
    }

    @Override
    public SingleOutput findCountryByName(String countryName){

        SingleOutput output;

        try {
            CountryEntity retrievedCountry =
                    countryRepository.findByCountryName(countryName);

            if (retrievedCountry != null &&
                    retrievedCountry.getCountryName() != null) {
                CountryDTO outputDTO = convertCountryEntityToDTO(retrievedCountry);

                output = new SingleOutput(OutputType.SUCCESS, outputDTO);
            } else {
                output = new SingleOutput(OutputType.SUCCESS, "Data not found for " + countryName);
            }
        }
        catch (Exception exception){
            output = new SingleOutput(OutputType.ERROR,"An error occured while fetching " +
                    countryName + " data.");
            loggerService.logOutputAndException(output, exception ,this.getClass());
        }

        return output;
    }

    CountryEntity convertCountryDTOToEntity(CountryDTO inputDTO){

        CountryEntity entityToInsert = new CountryEntity();

        try {
            entityToInsert.setCountryFlagUrl(inputDTO.getFlag_file_url());
            entityToInsert.setCountryCode(inputDTO.getCountry_code());
            entityToInsert.setCountryName(inputDTO.getName());
            entityToInsert.setCountryCapital(inputDTO.getCapital());
            entityToInsert.setCountryPopulation(inputDTO.getPopulation());
        }
        catch (Exception exception){
            loggerService.logOutputAndException(new OperationOutput(OutputType.ERROR,"Error while converting DTO of "
                                                    + inputDTO.getName()),
                                                exception ,
                                                this.getClass());
            return null;
        }

        return entityToInsert;

    }

    List<CountryEntity> convertDTOsToEntities(List<CountryDTO> inputDTOs){

        List<CountryEntity> outputEntities = new ArrayList<>();

        if(inputDTOs != null && inputDTOs.size() > 0) {

            inputDTOs.stream().forEach(countryDTO -> {
                CountryEntity entityToAddList = convertCountryDTOToEntity(countryDTO);
                if (entityToAddList != null)
                    outputEntities.add(entityToAddList);
            });
        }

        return outputEntities;
    }

    CountryBriefDTO convertCountryEntityToBriefDTO(CountryEntity inputEntity) {

        CountryBriefDTO outputDTO = new CountryBriefDTO();

        outputDTO.setCountry_code(inputEntity.getCountryCode());
        outputDTO.setName(inputEntity.getCountryName());

        return outputDTO;

    }

    CountryDTO convertCountryEntityToDTO(CountryEntity countryEntity){

        CountryDTO outputDTO = new CountryDTO(convertCountryEntityToBriefDTO(countryEntity));

        outputDTO.setCapital(countryEntity.getCountryCapital());
        outputDTO.setFlag_file_url(countryEntity.getCountryFlagUrl());
        outputDTO.setPopulation(countryEntity.getCountryPopulation());

        return outputDTO;

    }

    List<CountryBriefDTO> convertCountryEntityToBriefDTOList(List<CountryEntity> inputList){
        List<CountryBriefDTO> outputDTOList = new ArrayList<>();

        inputList.stream().forEach(countryEntity->{
            outputDTOList.add(convertCountryEntityToBriefDTO(countryEntity));
        });

        return outputDTOList;
    }

    List<CountryDTO> convertCountryEntityToDTOList(List<CountryEntity> inputList){
        List<CountryDTO> outputDTOList = new ArrayList<>();

        inputList.stream().forEach(countryEntity->{
            outputDTOList.add(convertCountryEntityToDTO(countryEntity));
        });

        return outputDTOList;
    }
}
