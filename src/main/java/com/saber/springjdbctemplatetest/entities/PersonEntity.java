package com.saber.springjdbctemplatetest.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonEntity  {
	private Integer id;
	private String firstName;
	private String lastName;
	private String nationalCode;
	private String mobile;
	private Integer age;
	private String email;
	
}