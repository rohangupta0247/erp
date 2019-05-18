package com.saptris.erp;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.service.spi.ServiceException;

public class UserManager {
	public static final int VALID_USER= 1;
	public static final int INVALID_PASSWORD= 2;
	public static final int INVALID_USERNAME= 3;
	
	private static User user;
	
	public static User getUser(){
		if(user!=null && user.equals(new User()))
			return null; 
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
	public static int validateLogin(String username, String pass, User fetchUser, int session_id) throws HibernateException{
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

				//add session info to user in database
				Transaction txn = hbnSession.beginTransaction(); 
				
			    u.setLogin_session(session_id);
			    hbnSession.save(u);
			    
			    txn.commit();
			    
				
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
		return getUser()!=null;
	}
	public static boolean isLoggedout() {
		return getUser()==null;
	}
	
	public static HttpSession getSession(HttpServletRequest request) {
		if(getUser()!=null) {
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
			else {
				
				//check from database if user session_id is same
				Session hbnSession = null;
				try{
					hbnSession = SessionFactoryBuilder.getDefaultSessionFactory().openSession();

					String jpql = "select u from User u where u.username = :name";
					User u = hbnSession.createQuery(jpql, User.class).setParameter("name", getUser().getUsername()).getSingleResult();

					if(u.getLogin_session()!=session.hashCode()) {
						return null;
					}
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
				
				
				return session;
			}
		}
		else
			return null;
	}
	
	public static void logout(HttpServletRequest request) {
		HttpSession session= request.getSession(false);
		if(session!=null)
			session.invalidate();
		user=null;
	}
}
