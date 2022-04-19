package com.saber.springjdbctemplatetest.dto;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<PersonDto> {
	@Override
	public PersonDto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		PersonDto personDto = new PersonDto();
		personDto.setFirstName(resultSet.getString("firstName"));
		personDto.setLastName(resultSet.getString("lastName"));
		personDto.setNationalCode(resultSet.getString("nationalCode"));
		personDto.setAge(resultSet.getInt("age"));
		personDto.setEmail(resultSet.getString("email"));
		personDto.setMobile(resultSet.getString("mobile"));
		return personDto;
	}
}
