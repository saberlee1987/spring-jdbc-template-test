package com.saber.springjdbctemplatetest.repositories;

import com.saber.springjdbctemplatetest.dto.DeleteResponseDto;
import com.saber.springjdbctemplatetest.dto.PersonDto;
import com.saber.springjdbctemplatetest.dto.PersonResponse;
import com.saber.springjdbctemplatetest.entities.PersonEntity;

import java.util.Optional;

public interface PersonRepository {
	Optional<PersonEntity> addPerson(PersonDto dto);
	Optional<PersonDto> findPersonByNationalCode(String nationalCode);
	Optional<PersonDto> updatePersonByNationalCode(PersonDto personDto,String nationalCode);
	PersonResponse findAllPersons();
	Optional<DeleteResponseDto> deletePersonByNationalCode(String nationalCode);
	
}