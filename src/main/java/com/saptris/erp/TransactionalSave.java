package com.saptris.erp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TransactionalSave {
	public static void save(Object o) {
		Session hbnSession = null;
		try{
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
}
