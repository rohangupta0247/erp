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
<%@page session="false"%>
<%
	HttpSession session= UserManager.getSession(request);
	String from= request.getRequestURI()+"?"+request.getQueryString();
	if(session==null){
		out.print("<a href=\"login?from="+from+"\">Login</a><br>");
		//response.sendRedirect("login?from="+from);
		//in this case we dont want further as it is main page
		//return;
	}
	else
		out.print(session.getAttribute("name")+"'s ERP"+
		"<br><a href=\"logout\">Logout</a><br>");
%>

<div class="jumbotron text-center" style="margin-bottom: 0">
		<h1>ERP</h1>
	</div>

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand" href="#">Menu</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="view?query=Company">Company</a></li>
				<li class="nav-item"><a class="nav-link"
					href="view?query=Warehouse">Warehouse</a></li>
				<li class="nav-item"><a class="nav-link"
					href="view?query=Item">Inventory</a></li>
			</ul>
		</div>
	</nav>


<a href="save?query=Company&status=add">Add Company</a>
<jsp:include page="footer.jsp" />
