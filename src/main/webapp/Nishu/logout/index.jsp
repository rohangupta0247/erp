<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ERP login</title>
</head>
<body>
Logout
logging out ...
<%@page session="false" %>
<%
request.getSession(false).invalidate();
response.sendRedirect("../"); 
%>
</body>
</html>