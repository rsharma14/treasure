package com.spring.servicetwo;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer{
	
	@LoadBalanced
	@Bean
	RestTemplate geRestTemplate() {
		return new RestTemplate();
	}
	 
//	 @Bean
//	 public Docket swaggerApi() {
//	  return new Docket(DocumentationType.SWAGGER_2)
//	   .select()
//	   .apis(RequestHandlerSelectors.basePackage("com.spring.servicetwo.controller"))
//	   .paths(PathSelectors.any())
//	   .build()
//	   .apiInfo(new ApiInfoBuilder().version("1.0").title("Employee API").description("Documentation Employee API v1.0").build());
//	 }

    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new AdminInterceptor());
//        registry.addInterceptor(new ThemeInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");

        // multiple urls (same is possible for `exludePathPatterns`)
    	registry.addInterceptor(new AdminSecurityInterceptor()).addPathPatterns( "/");
    }
 
}
