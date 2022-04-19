package com.saber.springjdbctemplatetest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
	private String firstName;
	private String lastName;
	private String nationalCode;
	private String mobile;
	private Integer age;
	private String email;
}