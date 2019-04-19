package com.saptris.erp.pmm;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;

import com.saptris.erp.EntityManager;
import com.saptris.erp.Mail;
import com.saptris.erp.MaintenanceAllUsers;
import com.saptris.erp.UserManager;

public class ReminderScheduler extends Thread {
	//private boolean running;
	private static Timer timer;
	
	static {
		timer= new Timer();
	}
	
	@Override
	public void run() {
		//running= true;
		
		System.out.println("Scheduler started...");
		
		//time is in 24hr format
		//Date reminderDateTimeTest= Date.from(LocalDateTime.of(2019, 3, 19, 14, 15, 00).atZone(ZoneId.systemDefault()).toInstant());
		//timer.schedule(new MaintenanceReminderTask(), reminderDateTimeTest);
		
		EntityManager maintenanceEntity= new EntityManager("MaintenanceAllUsers");
		boolean flag=false;
		
		for(Object maintenanceObj: maintenanceEntity.getAllEntity()){
			MaintenanceAllUsers maintenance= (MaintenanceAllUsers)maintenanceObj;
			Date reminderDate= maintenance.getMaintenance_time();
			LocalDateTime reminderDateTime= reminderDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			
			//time is in 24hr format
			//LocalDateTime reminderDateTime= LocalDateTime.of(2019, 3, 19, 14, 50, 00);
			LocalDateTime currentDateTime= LocalDateTime.now();
			if(reminderDateTime.isAfter(currentDateTime)) {
				System.out.println("scheduling "+maintenance.getMaintenance_id()+" @ "+reminderDateTime);
				timer.schedule(new MaintenanceReminderTask(UserManager.getUser(), maintenance), Date.from(reminderDateTime.atZone(ZoneId.systemDefault()).toInstant()));
				flag=true;
			}
		}
		
		if(!flag) {
			System.out.println("All reminders found were executed earlier. Currently no reminder left.");
		}
		
		/*while(running) {
			System.out.println("Scheduler running...");
			
			try {
			}
			catch(Exception ex) {
				System.out.println("Reminder Scheduler has got some unexpected error");
				ex.printStackTrace();
			}
			
			//giving scheduler a power nap just to decrease load
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				System.out.println("Reminder Scheduler was unexpectedly interrupted while taking a power nap");
				e.printStackTrace();
			}
		}*/
		System.out.println("Scheduler terminating...");
	}
	
	public static void stopScheduler() {
		timer.cancel();
		//running= false;
	}	
	
	
	public static void notifyForNewReminder(MaintenanceAllUsers maintenanceEntity) throws Exception {
		String message="You have recently added a new preventive maintenance reminder with following details :-";
		String[] attrn= new EntityManager("MaintenanceAllUsers").getAttributesName();
		int x=0,y=0;
		String records[]= maintenanceEntity.toString().split(EntityManager.separator);
		//[MaintenanceAllUsers{, id, item, date, desc, User, name, email, phone, }]
		//leave 0,1 and last index
		for(x=2;x<records.length-1;x++) {
			//skip user details
			if(records[x].startsWith("User")) {
				y++;
				continue;
			}
			message += "\n" + EntityManager.toNamingCase(attrn[y++]) + " : " + records[x];
		}
		
		//in case of server terminations, needed to schedule according to database in starting
		//and this will schedule ongoing reminders
		timer.schedule(new MaintenanceReminderTask(UserManager.getUser(), maintenanceEntity), maintenanceEntity.getMaintenance_time());
		
		Mail.sendMail(UserManager.getUser().getEmail(), "Added a new preventive maintenance reminder", message);
	}
}
