package com.salesstock.scheduler;

import static org.quartz.JobBuilder.newJob;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.salesstock.scheduler.task.TestTask;
import com.salesstock.util.Utils;

@Service
public class SchedulerServiceImpl implements SchedulerService {

	@Autowired
	SchedulerFactoryBean	scheduler;
	
	@Override
	public void testService() {
		System.out.println("generateReport");
		try
		{
			Scheduler obj = scheduler.getScheduler();
			JobDetail job = newJob(TestTask.class).build();
			CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(Utils.getUUID(), Utils.getUUID())
					.withSchedule(CronScheduleBuilder.cronSchedule("0 0/5 * * * ? *")).build();
				obj.scheduleJob(job, trigger);
		} catch (SchedulerException e)
		{
			e.printStackTrace();
		}
		
	}
}
