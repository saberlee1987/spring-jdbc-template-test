package com.saber.springjdbctemplatetest.services.impl;

import com.saber.springjdbctemplatetest.dto.HelloDto;
import com.saber.springjdbctemplatetest.services.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class HelloServiceImpl implements HelloService {
	
	@Override
	public HelloDto sayHello(String firstName, String lastName) {
		Map<String,Object> json = new HashMap<>();
		json.put("firstName",firstName);
		json.put("lastName",lastName);
		log.info("Request for sayHello with this parameters {}",json);
		String message = String.format("Hello %s %s",firstName,lastName);
		HelloDto helloDto = new HelloDto(message);
		log.info("Response for sayHello with parameters {} ===> {}",json,helloDto);
		return helloDto;
	}
}
