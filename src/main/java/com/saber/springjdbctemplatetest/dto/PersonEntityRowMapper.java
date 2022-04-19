package com.saber.springjdbctemplatetest.dto;

import com.saber.springjdbctemplatetest.entities.PersonEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonEntityRowMapper implements RowMapper<PersonEntity> {
	@Override
	public PersonEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		PersonEntity person = new PersonEntity();
		person.setId(resultSet.getInt("id"));
		person.setFirstName(resultSet.getString("firstName"));
		person.setLastName(resultSet.getString("lastName"));
		person.setNationalCode(resultSet.getString("nationalCode"));
		person.setAge(resultSet.getInt("age"));
		person.setEmail(resultSet.getString("email"));
		person.setMobile(resultSet.getString("mobile"));
		return person;
	}
}
