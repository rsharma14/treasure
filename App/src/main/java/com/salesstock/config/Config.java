package com.salesstock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.salesstock.intercepter.AdminSecurityInterceptor;
import com.salesstock.intercepter.TwoInterceptor;

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
	 
}
