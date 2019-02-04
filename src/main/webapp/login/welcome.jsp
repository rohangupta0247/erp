<%@page import="com.saptris.erp.db.User"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.cfg.Configuration"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ERP</title>
</head>
<body>
<%
	String username= request.getParameter("username");
	String password= request.getParameter("password");
	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	Session hbnSession = factory.openSession();
	
	String jpql = "select u from User u where u.username = :name";
	User u=null;
    try{
    	u = hbnSession.createQuery(jpql, User.class).setParameter("name", username).getSingleResult();
    	System.out.println("\n\n\nSearched: "+u+"\n\n");
	if(u.getUsername().equals(username) && u.getPass().equals(password)){
		out.print("Welcome "+u.getName());
	}
	else
		out.print("Wrong credentials");
	}
	catch(Exception e){
		out.print("Wrong credentials");
	}
	
    hbnSession.close();
    factory.close();
%>
</body>
</html>