<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page session="false" %>
<%
HttpSession session= request.getSession(false);
if(session!=null)
	session.invalidate();
//response.sendRedirect("/home"); //not goes to controller, directly redirects
%>
<script>
window.location.replace("home");
</script>