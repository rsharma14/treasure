package com.salesstock.intercepter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.salesstock.scheduler.SchedulerService;

@Component
public class AppOnStartup implements ApplicationListener<ContextRefreshedEvent>{
	private static final Logger LOG =LoggerFactory.getLogger(AppOnStartup.class);

	@Autowired
	SchedulerService serviceScheduler;

	public void onApplicationEvent(ContextRefreshedEvent ctx) {
		LOG.info("App started");
		serviceScheduler.testService();
	}
}
