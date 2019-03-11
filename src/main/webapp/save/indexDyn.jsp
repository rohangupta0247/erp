<%@page import="java.util.List"%>
<%@page import="com.saptris.erp.UserManager"%>
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
<%@page session="false" %>
<%
HttpSession session= UserManager.getSession(request);
if(session==null){
	out.print("<a href=\"login\">Login</a>");
	response.sendRedirect("../login");
	//in this case we dont want further excecutions
	return;
//System.out.println("message     "+session+" "+UserManager.isLoggedout());
}
else
	out.print(session.getAttribute("name").toString()+"'s ERP"+
	"<br><a href=\"../logout\">Logout</a><br>");
%>

<%!String []attributes, types; %>
<%
String query= request.getParameter("query");
String status= request.getParameter("status");
if(query==null)
	query= "Company";
if(status==null)
	status= "add";

EntityManager entityManager= new EntityManager(query);
attributes= entityManager.getAttributesName();
types= entityManager.getAttributesType();
%>

New <%=EntityManager.toNamingCase(query) %>
<form action="success.jsp?query=<%=query %>&status=<%=status %>" method="POST">
<%for(int i=0; i<attributes.length; i++){
	out.print(EntityManager.toNamingCase(attributes[i]));
	if(types[i].equals("text")){
		out.print("<input type=\"text\" name=\""+
			EntityManager.toRunningCase(attributes[i])+"\"><br>");
	}
	else if(types[i].equals("select")){
		//TODO user is null exception while refresh
		//in case of select, attribute name will be variable name of attribute class in the entity class
		EntityManager foreignEntity= new EntityManager(EntityManager.toNamingCase(attributes[i])/*.split(" ")[0]*/);
		List<?> foreignValues= foreignEntity.getAllEntity();
		
		out.print("<select name=\""+EntityManager.toRunningCase(attributes[i])+
		"\"><option value=\"--select--\">--select--</option>");
		for(int j=0;j<foreignValues.size();j++){
			//store id of entity in "value" and complete detail in inner text
			out.print("<option value=\""+EntityManager.toRunningCase(foreignValues.get(j).toString().split(EntityManager.separator)[1])+"\">"+
			/*EntityManager.toNamingCase(*/foreignValues.get(j).toString().split(EntityManager.separator)[1]+ " " +
					foreignValues.get(j).toString().split(EntityManager.separator)[2]/*)*/+"</option>");
		}
		out.print("</select><br>");
	}
} %>

<input type="submit" value="Save">
</form>
</body>
</html>