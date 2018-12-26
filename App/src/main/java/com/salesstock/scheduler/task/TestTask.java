package com.salesstock.scheduler.task;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DisallowConcurrentExecution
public class TestTask implements Job {
	private static final Logger LOG = LoggerFactory.getLogger(TestTask.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		LOG.info("TestTask-execute()");

	}
}
