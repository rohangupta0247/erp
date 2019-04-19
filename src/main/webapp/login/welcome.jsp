<%@page import="com.saptris.erp.SessionFactoryBuilder"%>
<%@page import="com.saptris.erp.JspStream"%>
<%@page import="com.saptris.erp.UserManager"%>
<%@page import="com.saptris.erp.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ERP</title>
</head>
<body>

<% SessionFactoryBuilder.setRealPath(getServletContext().getRealPath("")); %>

<%@page session="false" %>
<%
	HttpSession session=null;
	String username= request.getParameter("username");
	String password= request.getParameter("password");
	try{
	User u=new User();
	int status= UserManager.validateLogin(username, password,u);
	if(status==UserManager.VALID_USER){
		session= request.getSession();
		
		session.setAttribute("username", u.getUsername());
		session.setAttribute("name", u.getName());
		session.setAttribute("email", u.getEmail());
		session.setAttribute("pass", u.getPass());
		
		out.print("Welcome "+session.getAttribute("name").toString());
		
		String from= request.getParameter("from");
		if(from==null)
			from="../";
		response.sendRedirect(from);
	}
	else if(status==UserManager.INVALID_USERNAME){
		out.print("Not signed up yet, go to signup"+
	"<br><a href=../signup>SignUp</a>");
		response.sendRedirect("../signup");
	}
	else if(status==UserManager.INVALID_PASSWORD){
		out.print("Wrong password");
	}
	}
    catch(Exception e){
    	JspStream.printException(e,out);
    }
%>
</body>
</html>