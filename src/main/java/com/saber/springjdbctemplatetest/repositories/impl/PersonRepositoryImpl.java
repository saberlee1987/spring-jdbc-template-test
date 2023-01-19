package com.saber.springjdbctemplatetest.repositories.impl;

import com.saber.springjdbctemplatetest.dto.*;
import com.saber.springjdbctemplatetest.entities.PersonEntity;
import com.saber.springjdbctemplatetest.exceptions.ResourceDuplicationException;
import com.saber.springjdbctemplatetest.exceptions.ResourceNotFoundException;
import com.saber.springjdbctemplatetest.repositories.PersonRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public class PersonRepositoryImpl implements PersonRepository {
	
	private final JdbcTemplate jdbcTemplate;
	private static final String TABLE_NAME = "persons";
	
	public PersonRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public Optional<PersonEntity> addPerson(PersonDto dto) {
		Optional<PersonEntity> optionalPersonDto = findPersonWithNationalCode(dto.getNationalCode());
		if (optionalPersonDto.isPresent()) {
			throw new ResourceDuplicationException(String.format("this Person with nationalCode %s already exist", dto.getNationalCode()));
		}
		jdbcTemplate.update("insert into " + TABLE_NAME + " (firstName,lastName,nationalCode,mobile,age,email) VALUES (?,?,?,?,?,?)"
				, dto.getFirstName(), dto.getLastName(), dto.getNationalCode(), dto.getMobile(), dto.getAge(), dto.getEmail());
		return findEntityWithNationalCode(dto.getNationalCode());
	}
	
	@Override
	public Optional<PersonEntity> findPersonByNationalCode(String nationalCode) {
		Optional<PersonEntity> optionalPersonDto = findPersonWithNationalCode(nationalCode);
		if (optionalPersonDto.isEmpty()) {
			throw new ResourceNotFoundException(String.format("this Person with nationalCode %s does not exist", nationalCode));
		}
		return optionalPersonDto;
	}
	
	@Override
	public Optional<PersonEntity> updatePersonByNationalCode(PersonDto personDto, String nationalCode) {
		Optional<PersonEntity> optionalPersonDto = findPersonWithNationalCode(nationalCode);
		if (optionalPersonDto.isEmpty()) {
			throw new ResourceNotFoundException(String.format("this Person with nationalCode %s does not exist", nationalCode));
		}
		PersonEntity resultPersonDto = optionalPersonDto.get();
		resultPersonDto.setFirstName(personDto.getFirstName());
		resultPersonDto.setLastName(personDto.getLastName());
		resultPersonDto.setNationalCode(personDto.getNationalCode());
		resultPersonDto.setAge(personDto.getAge());
		resultPersonDto.setEmail(personDto.getEmail());
		resultPersonDto.setMobile(personDto.getMobile());
		this.jdbcTemplate.update("update " + TABLE_NAME + " set firstName=? , lastName=? , nationalCode=? , age=? , email=?, mobile=? where nationalCode=?",
				resultPersonDto.getFirstName(),
				resultPersonDto.getLastName(),
				resultPersonDto.getNationalCode(),
				resultPersonDto.getAge()
				, resultPersonDto.getEmail(),
				resultPersonDto.getMobile(), nationalCode);
		return findPersonByNationalCode(nationalCode);
		
	}
	@Override
	public PersonResponse findAllPersons() {
		List<PersonEntity> personDtoList = this.jdbcTemplate.query("select * from " + TABLE_NAME
				, new PersonEntityRowMapper());
		PersonResponse response = new PersonResponse();
		response.setPersons(personDtoList);
		return response;
	}
	
	@Override
	public Optional<DeleteResponseDto> deletePersonByNationalCode(String nationalCode) {
		Optional<PersonEntity> optionalPersonDto = findPersonByNationalCode(nationalCode);
		if (optionalPersonDto.isEmpty()) {
			throw new ResourceNotFoundException(String.format("this Person with nationalCode %s does not exist", nationalCode));
		}
		int deleted = this.jdbcTemplate.update("delete from " + TABLE_NAME + " where nationalCode=?", nationalCode);
		if (deleted > 0) {
			return Optional.of(new DeleteResponseDto(0, "your data deleted with successfully"));
		}
		return Optional.empty();
	}
	
	private Optional<PersonEntity> findEntityWithNationalCode(String nationalCode) {
		List<PersonEntity> entityList = this.jdbcTemplate.query("select * from " + TABLE_NAME + " where nationalCode=?"
				, new PersonEntityRowMapper(), nationalCode);
		return entityList.isEmpty() ? Optional.empty() : Optional.of(entityList.get(0));
	}
	
	private Optional<PersonEntity> findPersonWithNationalCode(String nationalCode) {
		List<PersonEntity> entityList = this.jdbcTemplate.query("select * from " + TABLE_NAME + " where nationalCode=?"
				, new PersonEntityRowMapper(), nationalCode);
		return entityList.isEmpty() ? Optional.empty() : Optional.of(entityList.get(0));
	}
}