package com.saptris.erp;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hibernate.HibernateException;

import com.sun.mail.util.MailConnectException;

public class Mail{
	final static public String emailAddress="saptristech@gmail.com";
	final static private String password="Therise@9tech";
	final static public String fromAddress="admin@saptriserp.com";
	final static public String fromName="Saptris ERP";
	
	private static class Auth extends Authenticator{
		protected PasswordAuthentication getPasswordAuthentication(){
			return new PasswordAuthentication(emailAddress,password);
		}
	}
	public static void sendMail(String to, String subject, String message/*, String attachmentFilePath*/) throws Exception{
		//String from= emailAddress;
		
		//Get session object
		Properties p= new Properties();
		p.put("mail.smtp.host","smtp.gmail.com");
		p.put("mail.smtp.socketFactory.port","465");
		p.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.auth","true");
		p.put("mail.smtp.port","465");
		
		//try{
			//Session s= Session.getDefaultInstance(p, new Auth());
			Session s= Session.getInstance(p, new Auth());
			
			//compose message
			MimeMessage mail= new MimeMessage(s);
			
			mail.setFrom(new InternetAddress(fromAddress, fromName));
			
			mail.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			mail.setSubject(subject);
			
			mail.setText(message);
			
			
			//requires activation.jar
			
			/*Multipart body= new MimeMultipart();
			
			MimeBodyPart mess= new MimeBodyPart();
			mess.setText(message);
			
			body.addBodyPart(mess);
			
			if(attachmentFilePath!=null){
				MimeBodyPart attach= new MimeBodyPart();
				FileDataSource fds= new FileDataSource(attachmentFilePath);
				attach.setDataHandler(new DataHandler(fds));
				attach.setFileName(fds.getName());
				
				body.addBodyPart(attach);
			}
			mail.setContent(body);
			*/
			
			//send mail
			try {
				Transport.send(mail);
			}
			catch(MailConnectException exc) {
				throw new HibernateException("Problem connecting to Internet to send mail", exc);
			}
		/*}
		catch(Exception e){
			e.printStackTrace();
		}*/
	}
}