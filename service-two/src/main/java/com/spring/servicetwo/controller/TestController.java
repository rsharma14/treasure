package com.spring.servicetwo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	private Environment env;
	
	static Map<Integer, String> db=new HashMap<>();
	
	public TestController() {
		System.out.println(restTemplate);
	}
	
	@GetMapping(value="get")
	public Object get() {
		System.out.println(restTemplate+"="+env.getProperty("local.server.port"));
		return restTemplate.getForObject("http://service-one/get",Object.class);
//		return restTemplate.getForObject("http://localhost:8001/get",Object.class);

//		return db;

	}
}
