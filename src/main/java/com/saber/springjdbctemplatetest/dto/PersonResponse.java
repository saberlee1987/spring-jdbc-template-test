package com.saber.springjdbctemplatetest.dto;

import com.saber.springjdbctemplatetest.entities.PersonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponse {
	private List<PersonEntity> persons;
}
