package com.saber.springjdbctemplatetest.advice;

import com.saber.springjdbctemplatetest.dto.ErrorResponseDto;
import com.saber.springjdbctemplatetest.dto.ServiceErrorResponseEnum;
import com.saber.springjdbctemplatetest.dto.ValidationDto;
import com.saber.springjdbctemplatetest.exceptions.OperationException;
import com.saber.springjdbctemplatetest.exceptions.ResourceDuplicationException;
import com.saber.springjdbctemplatetest.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class AdviceController extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception, HttpServletRequest request) {
		
		ErrorResponseDto errorResponse = new ErrorResponseDto();
		errorResponse.setCode(ServiceErrorResponseEnum.INPUT_VALIDATION_EXCEPTION.getCode());
		errorResponse.setMessage(ServiceErrorResponseEnum.INPUT_VALIDATION_EXCEPTION.getMessage());
		List<ValidationDto> validationDtoList = new ArrayList<>();
		for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
			ValidationDto validationDto = new ValidationDto();
			validationDto.setFieldName(violation.getPropertyPath().toString());
			validationDto.setConstraintMessage(violation.getMessage());
			validationDtoList.add(validationDto);
		}
		errorResponse.setValidationDetails(validationDtoList);
		
		log.error("Error for  handleConstraintViolationException with body ===> {}", errorResponse);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponseDto errorResponse = new ErrorResponseDto();
		errorResponse.setCode(ServiceErrorResponseEnum.INPUT_VALIDATION_EXCEPTION.getCode());
		errorResponse.setMessage(ServiceErrorResponseEnum.INPUT_VALIDATION_EXCEPTION.getMessage());
		List<ValidationDto> validationDtoList = new ArrayList<>();
		for (FieldError fieldError : exception.getFieldErrors()) {
			ValidationDto validationDto = new ValidationDto();
			validationDto.setFieldName(fieldError.getField());
			validationDto.setConstraintMessage(fieldError.getDefaultMessage());
			validationDtoList.add(validationDto);
		}
		errorResponse.setValidationDetails(validationDtoList);
		
		log.error("Error for  handleMethodArgumentNotValid with body ===> {}",  errorResponse);
		return ResponseEntity.status(status).body(errorResponse);
	}
	
	@ExceptionHandler(value = ResourceDuplicationException.class)
	public ResponseEntity<?> resourceDuplicationException(ResourceDuplicationException exception) {
		log.error("ResourceDuplicationException error ====> {}", exception.getMessage());
		ErrorResponseDto errorResponse = new ErrorResponseDto();
		errorResponse.setCode(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage(HttpStatus.BAD_REQUEST.toString());
		errorResponse.setOriginalMessage(String.format("{\"code\":%d,\"message\":\"%s\"}",
				HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
		log.error("ResourceDuplicationException error ====> {}", errorResponse);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	
	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException exception) {
		log.error("ResourceNotFoundException error ====> {}", exception.getMessage());
		ErrorResponseDto errorResponse = new ErrorResponseDto();
		errorResponse.setCode(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage(HttpStatus.BAD_REQUEST.toString());
		errorResponse.setOriginalMessage(String.format("{\"code\":%d,\"message\":\"%s\"}",
				HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
		log.error("ResourceNotFoundException error ====> {}", errorResponse);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	
	@ExceptionHandler(value = OperationException.class)
	public ResponseEntity<?> operationException(OperationException exception) {
		log.error("OperationException error ====> {}", exception.getMessage());
		ErrorResponseDto errorResponse = new ErrorResponseDto();
		errorResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		errorResponse.setOriginalMessage(String.format("{\"code\":%d,\"message\":\"%s\"}",
				HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()));
		log.error("OperationException error ====> {}", errorResponse);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}
}
