package com.saptris.erp.pmm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
		String response= sendSms(user.getPhone(), messageUser);
		if(!response.contains(",\"status\":\"success\"}"))
			System.out.println("User SMS was not successfully send for reminder");
		response= sendSms(maintenanceEntity.getMaintainer_phone(), messageMaintainer);
		if(!response.contains(",\"status\":\"success\"}"))
			System.out.println("Maintainer SMS was not successfully send for reminder");
	}
	
	private String sendSms(String phone, String message) {
		try {
			String test= "&test=true";
			
			// Construct data
			String apiKey = "apikey=" + "92Ty3xk/0Iw-AuJhW3j5isFqUQbVreZAeHCSMOEy8O";
			/*String */message = "&message=" + message;
			String sender = "&sender=" + "TXTLCL";
			String numbers = "&numbers=" + "91"+phone;
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender + test;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			
			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	}
}
