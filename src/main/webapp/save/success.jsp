<%@page import="java.io.IOException"%>
<%@page import="com.saptris.erp.UserManager"%>
<%@page session="false" %>
<%@page import="com.saptris.erp.JspStream"%>
<%@page import="com.saptris.erp.EntityManager"%>
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
HttpSession session= UserManager.getSession(request);
if(session==null){
	out.print("<a href=\"login\">Login</a>");
	response.sendRedirect("../login");
	//in this case we dont want further excecutions
	return;
}
else
	out.print(session.getAttribute("name").toString()+"'s ERP"+
	"<br><a href=\"../logout\">Logout</a><br>");
%>

<%/*
//to test all parameters
//neede final or effective final 'out' to use in annonymous inner class
final JspWriter fout= out;
request.getParameterMap().forEach((key, value)->{
try{
	fout.print(key+" : "+value[0]+"<br>");
}catch(IOException ex){ex.printStackTrace();}
});*/%>
<%
EntityManager entityManager= new EntityManager(request.getParameter("query"));
	try{
    	if(request.getParameter("status").equals("add"))
			entityManager.saveRecord(request.getParameterMap(), EntityManager.ADD_RECORD);
    	else if(request.getParameter("status").equals("update"))
			entityManager.saveRecord(request.getParameterMap(), EntityManager.UPDATE_RECORD);
%>
		saved
<%  
	if(request.getParameter("query").equals("MaintenanceAllUsers"))
		response.sendRedirect("../");
	else
		response.sendRedirect("../view?query="+request.getParameter("query"));
	}
    catch(Exception e){
    	JspStream.printException(e,out);
    }
%>
</body>
</html>