package com.saber.springjdbctemplatetest.controllers;

import com.saber.springjdbctemplatetest.dto.DeleteResponseDto;
import com.saber.springjdbctemplatetest.dto.ErrorResponseDto;
import com.saber.springjdbctemplatetest.dto.PersonDto;
import com.saber.springjdbctemplatetest.dto.PersonResponse;
import com.saber.springjdbctemplatetest.entities.PersonEntity;
import com.saber.springjdbctemplatetest.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
@Tag(name = "person api", description = "person service provide api")
@RequestMapping(value = "${service.api.base-path}/persons", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {
	private final PersonService personService;
	
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation( summary = "addPerson", description = "addPerson api", method = "POST",
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PersonDto.class)
							, examples = {@ExampleObject(name = "person", value = "{\"firstName\": \"saber\",\"lastName\": \"Azizi\",\"nationalCode\": \"0079028748\",\"age\": 34,\"email\": \"saberazizi66@yahoo.com\",\"mobile\": \"09365627895\"}")})
			})
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PersonEntity.class))}),
			@ApiResponse(responseCode = "400", description = "BAD_REQUEST",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
			@ApiResponse(responseCode = "406", description = "NOT_ACCEPTABLE",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))})
	})
	public ResponseEntity<PersonEntity> addPerson(@RequestBody @Valid PersonDto personDto) {
		log.info("Request for addPerson  ====> {}", personDto);
		
		PersonEntity response = this.personService.addPerson(personDto);
		log.info("Response for addPerson  ====> {}", response);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/find/{nationalCode}")
	@Operation( summary = "findByNationalCode", description = "findByNationalCode api", method = "GET",
			parameters = {
					@Parameter(name = "nationalCode", in = ParameterIn.PATH, required = true, example = "0079028748", description = "nationalCode")
			})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PersonDto.class))}),
			@ApiResponse(responseCode = "400", description = "BAD_REQUEST",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
			@ApiResponse(responseCode = "406", description = "NOT_ACCEPTABLE",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
			@ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
			@ApiResponse(responseCode = "504", description = "GATEWAY_TIMEOUT",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))})
		
	})
	public ResponseEntity<PersonDto> findByNationalCode(@PathVariable(name = "nationalCode")
														@NotBlank(message = "nationalCode is Required")
														@Size(min = 10, max = 10, message = "nationalCode must be 10 digit")
														@Pattern(regexp = "\\d+", message = "Please Enter correct nationalCode")
														@Valid
														@Parameter(name = "nationalCode", in = ParameterIn.PATH, example = "0079028748", required = true)
																String nationalCode) {
		
		log.info("Request for findByNationalCode  ====> {}", nationalCode);
		
		PersonDto response = this.personService.findPersonByNationalCode(nationalCode);
		log.info("Response for findByNationalCode  ====> {}", response);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/findAll")
	@Operation( summary = "findAll", description = "findAll api", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PersonResponse.class))}),
			@ApiResponse(responseCode = "400", description = "BAD_REQUEST",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
			@ApiResponse(responseCode = "406", description = "NOT_ACCEPTABLE",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
			@ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
			@ApiResponse(responseCode = "504", description = "GATEWAY_TIMEOUT",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))})
		
	})
	public ResponseEntity<PersonResponse> findAllPerson() {
		PersonResponse response = this.personService.findAllPersons();
		log.info("Response for findAllPerson  ====> {}", response);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping(value = "/update/{nationalCode}")
	@Operation( summary = "updateByNationalCode", description = "updateByNationalCode api", method = "PUT"
			, parameters = {
			@Parameter(name = "nationalCode", in = ParameterIn.PATH, required = true, example = "0079028748", description = "nationalCode")
	}
			, requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = PersonDto.class)
					, examples = {@ExampleObject(name = "person", value = "{\"firstName\": \"saber\",\"lastName\": \"Azizi\",\"nationalCode\": \"0079028748\",\"age\": 34,\"email\": \"saberazizi66@yahoo.com\",\"mobile\": \"09365627895\"}")}
			)}
	))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PersonDto.class))}),
			@ApiResponse(responseCode = "400", description = "BAD_REQUEST",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
			@ApiResponse(responseCode = "406", description = "NOT_ACCEPTABLE",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
			@ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
			@ApiResponse(responseCode = "504", description = "GATEWAY_TIMEOUT",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))})
		
	})
	public ResponseEntity<PersonDto> updatePersonByNationalCode(@PathVariable(name = "nationalCode")
																@NotBlank(message = "nationalCode is Required")
																@Size(min = 10, max = 10, message = "nationalCode must be 10 digit")
																@Pattern(regexp = "\\d+", message = "Please Enter correct nationalCode")
																@Parameter(name = "nationalCode", in = ParameterIn.PATH, example = "0079028748", required = true)
																		String nationalCode,
																@RequestBody @Valid
																		PersonDto dto) {
		log.info("Request for updatePersonByNationalCode by  nationalCode {} ====> {}", nationalCode, dto);
		
		PersonDto response = this.personService.updatePersonByNationalCode(dto,nationalCode);
		log.info("Response for updatePersonByNationalCode by  nationalCode {} ====> {}", nationalCode, response);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "/delete/{nationalCode}")
	@Operation(summary = "deletePersonByNationalCode", description = "deletePersonByNationalCode api", method = "DELETE",
			parameters = {
					@Parameter(name = "nationalCode", in = ParameterIn.PATH, required = true, example = "0079028748", description = "nationalCode")
			})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DeleteResponseDto.class))}),
			@ApiResponse(responseCode = "400", description = "BAD_REQUEST",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
			@ApiResponse(responseCode = "406", description = "NOT_ACCEPTABLE",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
			@ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
			@ApiResponse(responseCode = "504", description = "GATEWAY_TIMEOUT",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))})
		
	})
	public ResponseEntity<DeleteResponseDto> deletePersonByNationalCode(@PathVariable(name = "nationalCode")
																	  @NotBlank(message = "nationalCode is Required")
																	  @Size(min = 10, max = 10, message = "nationalCode must be 10 digit")
																	  @Pattern(regexp = "\\d+", message = "Please Enter correct nationalCode")
																	  @Valid
																	  @Parameter(name = "nationalCode", in = ParameterIn.PATH, example = "0079028748", required = true)
																			  String nationalCode) {
		log.info("Request for deletePersonByNationalCode by  nationalCode {} ", nationalCode);
		
		DeleteResponseDto response = this.personService.deletePersonByNationalCode(nationalCode);
		log.info("Response for deletePersonByNationalCode by  nationalCode {} ====> {}", nationalCode, response);
		return ResponseEntity.ok(response);
	}
}
