<%@page import="com.saptris.erp.JspStream"%>
<%@page import="com.saptris.erp.Mail"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String to= "rohangp227@gmail.com";
try{
	Mail.sendMail(to, "Test mail from ERP", "This is test mail from ERP. If you are reading this then it is functioning correctly.");
	out.print("test mail sent to "+to);
}
catch(Exception e){			
	JspStream.printException(e,out);
}	
%>
</body>
</html>