<%@page import="com.saptris.erp.Mail"%>
<%@page import="com.saptris.erp.JspStream"%>
<%@page import="com.saptris.erp.UserManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ERP</title>
</head>
<body>
<%!String subject= "Welcome to ERP";
   String message1= "Welcome ";
   String message2= " to ERP. You just signed up for this ERP"; %>
<%
	String username= request.getParameter("username");
	String email= request.getParameter("email");
	String name= request.getParameter("name");
	String phone= request.getParameter("phone");
	String password= request.getParameter("password");
    try{
    	UserManager.signup(username, email, name, phone, password);
		
    	//try{
			Mail.sendMail(email, subject, message1 + name + message2);
		/*}
		catch(Exception e){			
    		JspStream.printException(e,out);
		} */
					
		out.print("signed up for ERP, please login now");
				
		response.sendRedirect("../login");
    }
    catch(Exception e){
    	JspStream.printException(e,out);
    }
%>
<br>
<a href="../login">Login</a>
</body>
</html>