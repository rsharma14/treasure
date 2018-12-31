package com.salesstock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.salesstock.intercepter.AdminSecurityInterceptor;
import com.salesstock.intercepter.TwoInterceptor;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Config implements WebMvcConfigurer{
//	@Bean
//	public ViewResolver getViewResolver(){
//	    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//	    resolver.setPrefix("/WEB-INF/jsp/");
//	    resolver.setSuffix(".jsp");
//	   // resolver.setViewClass(JstlView.class);
//	    return resolver;
//	}
	
	    public void addInterceptors(InterceptorRegistry registry) {
//	        registry.addInterceptor(new AdminInterceptor());
//	        registry.addInterceptor(new ThemeInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");

	        // multiple urls (same is possible for `exludePathPatterns`)
	    	registry.addInterceptor(new AdminSecurityInterceptor()).addPathPatterns( "/a/**");
	    	registry.addInterceptor(new TwoInterceptor()).addPathPatterns("/secure/*", "/profile/**");
	    }
	    @Bean
	    public Docket api() {    
//	    	http://localhost:7070/salesstock/swagger-ui.html
	        return new Docket(DocumentationType.SWAGGER_2)          
	          .select()                                       
	          .apis(RequestHandlerSelectors.basePackage("com.salesstock"))
	          .build();
	    }
}
