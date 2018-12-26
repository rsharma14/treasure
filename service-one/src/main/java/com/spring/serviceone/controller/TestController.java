package com.spring.serviceone.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	static Map<String, String> db=new HashMap<>();
	
	public TestController() {
		db.put("a", "A");db.put("b", "B");db.put("c", "C");db.put("d", "D");
	}
	
	@GetMapping(value="get")
	public Map<String, String> get() {
		return db;

	}
}
