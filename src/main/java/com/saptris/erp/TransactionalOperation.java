package com.saptris.erp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TransactionalOperation {
	public static void save(Object o) {
		Session hbnSession = null;
		try{
		if(o instanceof MaintenanceAllUsers)
			hbnSession = SessionFactoryBuilder.getDefaultSessionFactory().openSession();
		else
			hbnSession = SessionFactoryBuilder.getUserSessionFactory().openSession();
		Transaction txn = hbnSession.beginTransaction(); 
		
	    hbnSession.save(o);
	    
	    txn.commit();
		}
		catch(Exception e){
			throw new HibernateException("Some unexpected error occured while saving via TransactionalSave", e);
		}
	    finally{
	    	if(hbnSession!=null)
		    	hbnSession.close();
	    }
	}

	public static void update(Object o) {
		Session hbnSession = null;
		try{
			hbnSession = SessionFactoryBuilder.getUserSessionFactory().openSession();
			Transaction txn = hbnSession.beginTransaction(); 
			
			hbnSession.update(o);
			
			txn.commit();
		}
		catch(Exception e){
			throw new HibernateException("Some unexpected error occured while updating via TransactionalSave", e);
		}
		finally{
			if(hbnSession!=null)
				hbnSession.close();
		}
	}
	
	//TODO when fixed all constraints can this be removed ??
	@SuppressWarnings("finally")
	public static boolean delete(Object o) {
		//TODO all constraints are handled in postgresql not in others
		boolean status=true;
		Session hbnSession = null;
		try{
			hbnSession = SessionFactoryBuilder.getUserSessionFactory().openSession();
			Transaction txn = hbnSession.beginTransaction(); 
			
			hbnSession.delete(o);
			
			txn.commit();
		}
		catch(Exception e){
			status=false;
			throw new HibernateException("Some unexpected error occured while deleting via TransactionalOpeartion", e);
		}
		finally{
			if(hbnSession!=null)
				hbnSession.close();
			return status;
		}
	}
}
