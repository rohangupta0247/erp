package com.saptris.erp;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.hibernate.service.spi.ServiceException;

public class SessionFactoryBuilder {
	private static SessionFactory singletonObjectForUser;
	private static SessionFactory singletonObject;
	private SessionFactoryBuilder() {}
	public static SessionFactory getDefaultSessionFactory() throws HibernateException{
		if(singletonObjectForUser==null) {
			try {
				singletonObjectForUser=new Configuration().configure("hibernateUser.cfg.xml").buildSessionFactory();
			}			
			catch(ServiceException e) {
				throw e;
			}
			catch(Exception e) {
				throw new HibernateException("Some unexpected error occured while building default SessionFactory",e);
			}
		}
		return singletonObjectForUser;
	}
	
	private static class UserNamePhysicalNamingStrategy implements PhysicalNamingStrategy{

		public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment jdbcEnvironment) {
			return name;
		}

		public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment jdbcEnvironment) {
			return name;
		}

		public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
			if(UserManager.user==null)
				return null;
			return Identifier.toIdentifier(UserManager.user.getUsername()+"_"+EntityManager.toRunningCase(name.getText()));
		}

		public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
			return name;
		}

		public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
			return name;
		}
		
	}
	
	public static SessionFactory getUserSessionFactory() throws HibernateException{
		if(UserManager.user==null)
			throw new HibernateException("Some unexpected error occured while building user based SessionFactory: User is null");
		if(singletonObject==null) {
			try {
				Configuration cfg= new Configuration();
				cfg.setPhysicalNamingStrategy(new UserNamePhysicalNamingStrategy());
				Configuration cfgNew= cfg.configure("hibernate.cfg.xml");
				singletonObject= cfgNew.buildSessionFactory();
			}
			catch(Exception e) {
				throw new HibernateException("Some unexpected error occured while building user based SessionFactory",e);
			}
		}
		return singletonObject;
	}
}
