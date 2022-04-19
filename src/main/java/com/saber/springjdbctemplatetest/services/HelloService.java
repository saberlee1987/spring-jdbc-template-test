package com.saber.springjdbctemplatetest.services;

import com.saber.springjdbctemplatetest.dto.HelloDto;

public interface HelloService {
	HelloDto sayHello(String firstName,String lastName);
}