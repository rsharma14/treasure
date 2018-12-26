package com.salesstock.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages= {"com.salesstock.controller","com.salesstock.*"})
@EntityScan(basePackages= {"com.salesstock.entity"})
@Import(value= {Config.class})
@EnableAutoConfiguration
public class MainApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MainApplication.class);
	}
}
