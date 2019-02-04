<%@page import="com.saptris.erp.db.User"%>
<%@page import="org.hibernate.Transaction"%>
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
	String email= request.getParameter("email");
	String name= request.getParameter("name");
	String phone= request.getParameter("phone");
	String password= request.getParameter("password");
	/*
	String username= (String)request.getAttribute("username");
	String email= (String)request.getAttribute("email");
	String name= (String)request.getAttribute("name");
	String phone= (String)request.getAttribute("phone");
	String password= (String)request.getAttribute("password");
	*/
	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	Session hbnSession = factory.openSession();
	Transaction t = hbnSession.beginTransaction(); 
	
    User u=new User(username, email, name, phone, password);
    System.out.println("\n\n\nNew user signedup: "+u+"\n\n\n");
    hbnSession.save(u);
    
    out.print("signed up for ERP, please login now");

    t.commit();
    hbnSession.close();
	factory.close();
%>
<br>
<a href="../login">Login</a>
</body>
</html>