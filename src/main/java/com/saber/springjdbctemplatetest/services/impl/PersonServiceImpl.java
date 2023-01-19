package com.saber.springjdbctemplatetest.services.impl;

import com.saber.springjdbctemplatetest.dto.DeleteResponseDto;
import com.saber.springjdbctemplatetest.dto.PersonDto;
import com.saber.springjdbctemplatetest.dto.PersonResponse;
import com.saber.springjdbctemplatetest.entities.PersonEntity;
import com.saber.springjdbctemplatetest.exceptions.OperationException;
import com.saber.springjdbctemplatetest.repositories.PersonRepository;
import com.saber.springjdbctemplatetest.services.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {
	private final PersonRepository personRepository;
	
	public PersonServiceImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	@Override
	@Transactional
	public PersonEntity addPerson(PersonDto dto) {
		log.info("Request for add person with body {}", dto);
		Optional<PersonEntity> personEntityOptional = personRepository.addPerson(dto);
		if (personEntityOptional.isEmpty()) {
			throw new OperationException("Error occurred for addPerson operation Please contact by admin system");
		}
		PersonEntity personEntity = personEntityOptional.get();
		log.info("Response for add person with body {}", personEntity);
		return personEntity;
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public PersonEntity findPersonByNationalCode(String nationalCode) {
		log.info("Request for find person with nationalCode {}", nationalCode);
		Optional<PersonEntity> personEntityOptional = personRepository.findPersonByNationalCode(nationalCode);
		if (personEntityOptional.isEmpty()) {
			throw new OperationException("Error occurred for findPersonByNationalCode operation Please contact by admin system");
		}
		PersonEntity responseDto = personEntityOptional.get();
		log.info("Response for find person with nationalCode {} with body {}", nationalCode, responseDto);
		return responseDto;
	}
	
	@Override
	@Transactional
	public PersonEntity updatePersonByNationalCode(PersonDto personDto, String nationalCode) {
		log.info("Request for update person with nationalCode {} with body {}", nationalCode, personDto);
		
		Optional<PersonEntity> personEntityOptional = personRepository.updatePersonByNationalCode(personDto, nationalCode);
		if (personEntityOptional.isEmpty()) {
			throw new OperationException("Error occurred for updatePersonByNationalCode operation Please contact by admin system");
		}
		PersonEntity responseDto = personEntityOptional.get();
		log.info("Response for find person with nationalCode {} with body {}", nationalCode, responseDto);
		return responseDto;
	}
	
	@Override
	@Transactional(readOnly = true)
	public PersonResponse findAllPersons() {
		log.info("Request for findAll person ");
		PersonResponse response = personRepository.findAllPersons();
		log.info("Response for findAll person with body {}", response);
		return response;
	}
	
	@Override
	@Transactional
	public DeleteResponseDto deletePersonByNationalCode(String nationalCode) {
		log.info("Request for delete person with nationalCode {}", nationalCode);
		Optional<DeleteResponseDto> optionalDeleteResponseDto = personRepository.deletePersonByNationalCode(nationalCode);
		if (optionalDeleteResponseDto.isEmpty()) {
			throw new OperationException("Error occurred for deletePersonByNationalCode operation Please contact by admin system");
		}
		DeleteResponseDto responseDto = optionalDeleteResponseDto.get();
		log.info("Response for delete person with nationalCode {} with body {}", nationalCode, responseDto);
		return responseDto;
	}
}
