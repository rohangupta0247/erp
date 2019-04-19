package com.saptris.erp.pmm;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ReminderServletContextListener implements ServletContextListener {

	private ReminderScheduler reminderScheduler;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Reminder Scheduler to start");
		reminderScheduler= new ReminderScheduler();
		reminderScheduler.start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ReminderScheduler.stopScheduler();
		try {
			reminderScheduler.join();
		} catch (InterruptedException e) {
			System.out.println("Reminder Scheduler was unexpectedly interrupted");
			e.printStackTrace();
		}
		System.out.println("Reminder Scheduler stopped");
	}
}
