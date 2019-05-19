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

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark" id="homeNavBar">
		<a class="navbar-brand" href="#"><%= condition %></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
				<% if(session==null){ %>
				<li class="nav-item"><a class="nav-link" href="login">LogIn</a></li>
				<li class="nav-item"><a class="nav-link" href="signup">SignUp</a></li>
				<% } else { %>
				<li class="nav-item"><a class="nav-link" href="logout">LogOut</a></li>
				<% } %>
			</ul>
		</div>
	</nav>



<style>
body {
  font-family: Arial, Helvetica, sans-serif;
}

.flip-card {
  background-color: transparent;
  width: 300px;
  height: 300px;
  perspective: 1000px;
}

.flip-card-inner {
  position: relative;
  width: 100%;
  height: 100%;
  text-align: center;
  transition: transform 0.6s;
  transform-style: preserve-3d;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
}

.flip-card:hover .flip-card-inner {
  transform: rotateY(180deg);
}

.flip-card-front, .flip-card-back {
  position: absolute;
  width: 100%;
  height: 100%;
  backface-visibility: hidden;
}

.flip-card-front {
  background-color: #bbb;
  color: black;
  z-index: 2;
}

.flip-card-back {
  background-color: #2980b9;
  color: white;
  transform: rotateY(180deg);
  z-index: 1;
}
</style>

<div class="flip-card">
  <div class="flip-card-inner">
    <div class="flip-card-front">
      <img src="image?image=comapny-512x512.png" alt="Comapny" style="width:300px;height:300px;">
    </div>
    <div class="flip-card-back">
      <h1>Company</h1> 
      <p>Manage your company</p> 
      <p>Set all company related details</p>
      <div align="center">
		<a href="view" class="btn btn-info" style="margin-top:20px;" role="button">View Company</a>
	</div>
    </div>
  </div>
</div>

<div class="flip-card">
  <div class="flip-card-inner">
    <div class="flip-card-front">
      <img src="image?image=payroll-800x800.png" alt="Payroll" style="width:300px;height:300px;">
    </div>
    <div class="flip-card-back">
      <h1>Payroll</h1> 
      <p>Manage your employees' salary</p> 
      <p>Set all employee related details</p>
      <div align="center">
		<a href="payroll" class="btn btn-info" style="margin-top:20px;" role="button">Open Payroll</a>
	</div>
    </div>
  </div>
</div>



<jsp:include page="footer.jsp" />
