package com.saber.springjdbctemplatetest.dto;

import lombok.Data;

@Data
public class ValidationDto {
	private String fieldName;
	private String constraintMessage;
}
