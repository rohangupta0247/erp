package com.saptris.erp.pmm;

import java.util.TimerTask;

import org.hibernate.HibernateException;

import com.saptris.erp.EntityManager;
import com.saptris.erp.Mail;
import com.saptris.erp.MaintenanceAllUsers;
import com.saptris.erp.User;

public class MaintenanceReminderTask extends TimerTask{
	User user;
	MaintenanceAllUsers maintenanceEntity;
	public MaintenanceReminderTask(User user, MaintenanceAllUsers maintenanceEntity) {
		super();
		this.user= user;
		this.maintenanceEntity= maintenanceEntity;
	} 
	@Override
	public void run() {
		String subjectUser="Reminder: Preventive Maintenance Reminder";
		String subjectMaintainer="Preventive Maintenance Scheduled";
		String messageUser="This is a reminder to remind that you have scheduled a preventive maintenance with following details :-";
		String messageMaintainer="This is to inform you that you have a scheduled preventive maintenance with following details :-";
		String[] attrn= new EntityManager("MaintenanceAllUsers").getAttributesName();
		int x=0,y=0;
		String records[]= maintenanceEntity.toString().split(EntityManager.separator);
		//[MaintenanceAllUsers{, id, item, time, desc, User, name, email, phone, }]
		//leave 0,1 and last index
		for(x=2;x<records.length-1;x++) {
			//skip user details
			if(records[x].startsWith("User")) {
				y++;
				continue;
			}
			messageUser += "\n" + EntityManager.toNamingCase(attrn[y]) + " : " + records[x];
			messageMaintainer += "\n" + EntityManager.toNamingCase(attrn[y]) + " : " + records[x];
			y++;
		}
		
		//send mail
		try {
			Mail.sendMail(user.getEmail(), subjectUser, messageUser);
			Mail.sendMail(maintenanceEntity.getMaintainer_email(), subjectMaintainer, messageMaintainer);
		} catch (Exception e) {
			throw new HibernateException("Some unexpected error occured while sending mail for reminder task", e);
		}
		
		//send sms
		
	}
}
