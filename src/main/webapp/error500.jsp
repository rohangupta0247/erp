<%@page import="java.io.PrintWriter"%>
<html>
<head>
<title>ERP</title>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<!-- Bootstrap CSS -->
<!-- <link rel="stylesheet" href="bootstrap.min.css"> -->
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- <link rel="stylesheet" type="text/css" href="bootstrap.css"> -->

</head>
<body>

<%@page import="com.saptris.erp.UserManager"%>
<%@page session="false" isELIgnored="false" isErrorPage="true"%>
<%
	HttpSession session= UserManager.getSession(request);
	String from= request.getRequestURI()+"?"+request.getQueryString();
	String condition="Menu";
	/*if(session==null){
		out.print("<a href=\"login?from="+from+"\">Login</a><br>");
		//response.sendRedirect("login?from="+from);
		//in this case we dont want further as it is main page
		//return;
	}
	else
		out.print(session.getAttribute("name")+"'s ERP"+
		"<br><a href=\"logout\">Logout</a><br>");
	*/
	if(session!=null){
		condition= session.getAttribute("name").toString();
	}
%>

<div class="jumbotron text-center" style="margin-bottom: 0">
		<h1>ERP</h1>
	</div>


<h2 class="text-center">Some unexpected internal server error has occured !<br>
<img alt="ERROR 500: Internal Server Error" src="image?image=error500.jpg">
</h2>

<jsp:include page="footer.jsp" />
<%
try{
	exception.printStackTrace();
}
catch(NullPointerException ex){
	System.out.println("exception object was null in error page");
}
%>
}