<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Clear screen</title>
</head>
<body>
<%System.out.println("\n\n\n"+new Date()+"\n\n\n"); %>
Console was reset
</body>
</html>