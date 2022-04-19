package com.saber.springjdbctemplatetest.controllers;

import com.saber.springjdbctemplatetest.dto.HelloDto;
import com.saber.springjdbctemplatetest.services.HelloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${service.api.base-path}/hello", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "hello", description = "Hello Service")
public class HelloController {
	
	private final HelloService helloService;
	
	public HelloController(HelloService helloService) {
		this.helloService = helloService;
	}
	
	@GetMapping(value = "/sayHello")
	@Operation(description = "say hello service", summary = "SayHello", parameters = {
			@Parameter(name = "firstName", in = ParameterIn.QUERY, required = true, example = "saber"),
			@Parameter(name = "lastName", in = ParameterIn.QUERY, required = true, example = "azizi"),
	})
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "OK",
							content = {@Content(mediaType = "application/json", schema = @Schema(implementation = HelloDto.class))})
			}
	)
	public ResponseEntity<HelloDto> sayHello(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {
		HelloDto helloDto = this.helloService.sayHello(firstName, lastName);
		return ResponseEntity.ok(helloDto);
	}
}
