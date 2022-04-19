package com.saber.springjdbctemplatetest.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class AppConfig {
	
	@Bean
	public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
		return new Jackson2ObjectMapperBuilder()
				.serializationInclusion(JsonInclude.Include.NON_EMPTY)
				.serializationInclusion(JsonInclude.Include.NON_NULL)
				.indentOutput(true)
				.failOnEmptyBeans(false)
				.failOnUnknownProperties(false)
				.featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS, SerializationFeature.FAIL_ON_SELF_REFERENCES)
				.featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, DeserializationFeature.FAIL_ON_INVALID_SUBTYPE)
				.featuresToEnable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT,
						DeserializationFeature.ACCEPT_FLOAT_AS_INT,
						DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,
						DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY
				);

	}
}
