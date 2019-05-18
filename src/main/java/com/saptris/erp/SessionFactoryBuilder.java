package com.saptris.erp;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Entity;

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
	private static User savedUser;
	private static String realPath;
	private static final String relativeClassPath= "WEB-INF"+File.separator+"classes"+File.separator;
	private static final String[] entityPackages= {"com.saptris.erp.db","com.saptris.erp.hrm.db"};
	private static final String[] globalentityPackages= {"com.saptris.erp"};
	
	public static HashMap<String, String> classesMapping= new HashMap<>();
	
	private SessionFactoryBuilder() {}
	public static SessionFactory getDefaultSessionFactory() throws HibernateException{
		if(singletonObjectForUser==null) {
			try {
				if(realPath==null)
					throw new IllegalStateException("The real path of web application was not set, use \" <% SessionFactoryBuilder.setRealPath(getServletContext().getRealPath(\"\")); %> \" in servlet");
				
				//singletonObjectForUser=new Configuration().configure("hibernateUser.cfg.xml").buildSessionFactory();
				Configuration cfg= new Configuration();

				for(String pack: globalentityPackages) {
					for(Class<?> c: SessionFactoryBuilder.getAllClasses(realPath+relativeClassPath , pack)) {
						if(c.getAnnotation(Entity.class)!=null) {
							cfg.addAnnotatedClass(c);
							System.out.println(c);
							classesMapping.put(c.getSimpleName(), pack+".");
						}
					}
				}

				//cfg.setPhysicalNamingStrategy(new UserNamePhysicalNamingStrategy());
				//Configuration cfgNew= cfg.configure("hibernateUser.cfg.xml");
				Configuration cfgNew= cfg.configure();
				singletonObjectForUser=cfgNew.buildSessionFactory();
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
			if(UserManager.getUser()==null)
				return null;
			
			String nameOfTable= EntityManager.toUnderscoreCaseFromCamelCase(name.getText());
			//if(nameOfTable.equals("maintenance_all_users") || nameOfTable.equals("user"))
				//return Identifier.toIdentifier(nameOfTable);
			
			return Identifier.toIdentifier(UserManager.getUser().getUsername()+"_"+nameOfTable);
		}

		public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
			return name;
		}

		public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
			return name;
		}
		
	}
	
	public static SessionFactory getUserSessionFactory() throws HibernateException{
		if(UserManager.getUser()==null)
			throw new HibernateException("Some unexpected error occured while building user based SessionFactory: User is null");
		if(singletonObject!=null) {
			//in case user's singelton SessionFactory was created but then some other user logins just after this
			//then compare saved user with current user
			if(savedUser!=UserManager.getUser())
				singletonObject= null;
		}
		if(singletonObject==null) {
			try {
				if(realPath==null)
					throw new IllegalStateException("The real path of web application was not set, use \" <% SessionFactoryBuilder.setRealPath(getServletContext().getRealPath(\"\")); %> \" in servlet");
				
				Configuration cfg= new Configuration();
				
				for(String pack: entityPackages) {
					for(Class<?> c: SessionFactoryBuilder.getAllClasses(realPath+relativeClassPath , pack)) {
						if(c.getAnnotation(Entity.class)!=null) {
							cfg.addAnnotatedClass(c);
							System.out.println(c);
							classesMapping.put(c.getSimpleName(), pack+".");
						}
					}
				}
					
				cfg.setPhysicalNamingStrategy(new UserNamePhysicalNamingStrategy());
				//Configuration cfgNew= cfg.configure("hibernate.cfg.xml");
				Configuration cfgNew= cfg.configure();
				singletonObject= cfgNew.buildSessionFactory();
				//save user details for SessionFactory
				savedUser= UserManager.getUser();
			}
			catch(Exception e) {
				throw new HibernateException("Some unexpected error occured while building user based SessionFactory",e);
			}
		}
		return singletonObject;
	}
	
	public static void setRealPath(String realPathOfApplication) {
		System.out.println("real path of application set to: "+realPathOfApplication);
		realPath= realPathOfApplication;
	}
	
	public static Class<?>[] getAllClasses(String classPath, String packageName){
		//System.out.println(System.getProperty("user.dir"));
		if(classPath==null)
			classPath="";
		else if(! classPath.endsWith(File.separator))
			classPath+= File.separator;
		if(! packageName.endsWith("."))
			packageName+= ".";
		String temp= "";
		for(char c: packageName.toCharArray()) {
			if(c!='.')
				temp+=c;
			else
				temp+=File.separator;
			
		}
		
		File dir= new File(classPath+temp);
		if(!dir.isDirectory())
			return null;
		String namestemp[]= dir.list();
		ArrayList<String> liststr= new ArrayList<>();
		for(String stemp: namestemp) {
			if(stemp.endsWith(".class"))
				liststr.add(stemp);
		}
		String names[]= liststr.toArray(new String[] {});
		if(names==null)
			return null;
		Class<?> res[]= new Class<?>[names.length];
			try {
				for(int i=0;i<names.length;i++)
					//classname will not have any '.', so only one '.' in complete file name
					res[i]= Class.forName(packageName+names[i].split("\\.")[0]);
			} catch (ClassNotFoundException e) {
				throw new HibernateException("Some unexpected error occured while getting all classes in package",e);
			}
		return res;
	}
}
