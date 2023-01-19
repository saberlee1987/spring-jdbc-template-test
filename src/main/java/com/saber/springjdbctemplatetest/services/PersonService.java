package com.saber.springjdbctemplatetest.services;

import com.saber.springjdbctemplatetest.dto.DeleteResponseDto;
import com.saber.springjdbctemplatetest.dto.PersonDto;
import com.saber.springjdbctemplatetest.dto.PersonResponse;
import com.saber.springjdbctemplatetest.entities.PersonEntity;

public interface PersonService {
	PersonEntity addPerson(PersonDto dto);
	PersonEntity findPersonByNationalCode(String nationalCode);
	PersonEntity updatePersonByNationalCode(PersonDto personDto,String nationalCode);
	PersonResponse findAllPersons();
	DeleteResponseDto deletePersonByNationalCode(String nationalCode);
}
