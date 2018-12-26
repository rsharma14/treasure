package com.spring.servicetwo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer{
	
	@Bean
	RestTemplate geRestTemplate() {
		return new RestTemplate();
	}
	 
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new AdminInterceptor());
//        registry.addInterceptor(new ThemeInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");

        // multiple urls (same is possible for `exludePathPatterns`)
    	registry.addInterceptor(new AdminSecurityInterceptor()).addPathPatterns( "/");
    }
 
}
