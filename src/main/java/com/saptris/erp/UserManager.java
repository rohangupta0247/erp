package com.saptris.erp;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.service.spi.ServiceException;

public class UserManager {
	public static int VALID_USER= 1;
	public static int INVALID_PASSWORD= 2;
	public static int INVALID_USERNAME= 3;
	
	/*private*/ static User user;
	public static User getUser(){
		return user;
	}
	
	public static void signup(String username, String email, String name, String phone, String pass) throws HibernateException{
		addUser(username, email, name, phone, pass);
	}
	private static void addUser(String username, String email, String name, String phone, String pass) throws HibernateException{
		Session hbnSession = null;
		try{
		hbnSession = SessionFactoryBuilder.getDefaultSessionFactory().openSession();
		Transaction txn = hbnSession.beginTransaction(); 
		
	    User u=new User(username, email, name, phone, pass);
	    hbnSession.save(u);
	    
	    txn.commit();
	    
	    user=u;
	    //to setup all other data during signup
	    SessionFactoryBuilder.getUserSessionFactory();
		}
		catch(Exception e){
			throw new HibernateException("Some unexpected error occured while singing up new user", e);
		}
	    finally{
		    if(hbnSession!=null)
		    	hbnSession.close();
	    }
	}
	public static int validateLogin(String username, String pass, User fetchUser) throws HibernateException{
		Session hbnSession = null;
		try{
			hbnSession = SessionFactoryBuilder.getDefaultSessionFactory().openSession();

			String jpql = "select u from User u where u.username = :name";
			User u = hbnSession.createQuery(jpql, User.class).setParameter("name", username).getSingleResult();

			if(u.equalsLoginCase(new User(username, pass))) {
				fetchUser.setUsername(u.getUsername());
				fetchUser.setEmail(u.getEmail());
				fetchUser.setName(u.getName());
				fetchUser.setPhone(u.getPhone());
				
				user=u;

				//to setup all other data if any changes were made to database schema at every login
			    SessionFactoryBuilder.getUserSessionFactory();
				
				return VALID_USER;
			}
			else
				return INVALID_PASSWORD;
		}
		catch(NoResultException e){
			return INVALID_USERNAME;
		}
		catch(ServiceException e){
			throw new HibernateException("Error connecting to database server",e);
		}
		catch(Exception e){
			throw new HibernateException("Some unexpected error occured while validating user for login", e);
		}
		finally{
			if(hbnSession!=null)
		    	hbnSession.close();
		}
	}
	
	public static boolean isLoggedin() {
		return user!=null;
	}
	public static boolean isLoggedout() {
		return user==null;
	}
	
	public static HttpSession getSession(HttpServletRequest request) {
		if(user!=null) {
			HttpSession session= request.getSession(false);
			//in some cases after updation in a jsp, session was returned but no attribute was attached
			//so invalidate the user in that case and returning null
			if(session==null)
				return null;
			else if(session.getAttribute("username")==null) {
				session.invalidate();
				user=null;
				return null;
			}
			else
				return session;
		}
		else
			return null;
	}
}
