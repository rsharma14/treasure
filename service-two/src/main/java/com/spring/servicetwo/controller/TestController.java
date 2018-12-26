package com.spring.servicetwo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

	@Autowired
	RestTemplate restTemplate;
	
	static Map<Integer, String> db=new HashMap<>();
	
	public TestController() {
		System.out.println(restTemplate);
	}
	
	@GetMapping(value="get")
	public Object get() {
		System.out.println(restTemplate);
		return restTemplate.getForObject("http://one-service/get",Object.class);
//		return db;

	}
}
