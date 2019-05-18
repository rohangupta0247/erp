<%@page import="com.saptris.erp.UserManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page session="false" %>
<%
UserManager.logout(request);
//response.sendRedirect("/home"); //not goes to controller, directly redirects
%>
<script>
window.location.replace("home");
</script>