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
<div class="container" style="background-color:lavender;">

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

<div id="demo" class="carousel slide" data-ride="carousel" align="center" style="background-color:black;">
  <ul class="carousel-indicators">
    <li data-target="#demo" data-slide-to="0" class="active"></li>
    <li data-target="#demo" data-slide-to="1"></li>
    <li data-target="#demo" data-slide-to="2"></li>
  </ul>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="image?image=erp002.png" alt="WERP" width="600" height="100" >
      <div class="carousel-caption">
        <!--h3 class="text-body">Web ERP</h3-->
        <!--p class="bg-dark text-white">Web Enterprise Resources Planning</p-->
      </div>   
    </div>
    <div class="carousel-item">
      <img src="image?image=erp003.png" alt="WERP" width="600" height="100">
      <div class="carousel-caption">
        <!--h3 class="bg-dark text-white">Web ERP</h3-->
        <!--p class="bg-dark text-white">Easily manage your enterprise with WERP</p-->
      </div>   
    </div>
    <div class="carousel-item">
      <img src="image?image=erp004.png" alt="WERP" width="600" height="100">
      <div class="carousel-caption">
        <!--h3 class="bg-dark text-white">Web ERP</h3-->
        <!--p class="bg-dark text-white">Thank you for using WERP!</p-->
      </div>   
    </div>
  </div>
  <a class="carousel-control-prev" href="#demo" data-slide="prev">
    <span class="carousel-control-prev-icon"></span>
  </a>
  <a class="carousel-control-next" href="#demo" data-slide="next">
    <span class="carousel-control-next-icon"></span>
  </a>
</div>

<br>
<div class="row">
  <!--div class="card-deck"-->
  
<div class="col">
    <div class="flip-card">
  <div class="flip-card-inner">
    <div class="flip-card-front">
      <img src="image?image=comapny-512x512.png" alt="Comapny" style="width:300px;height:300px;">
	  <!--img src="comapny-512x512.png" alt="Comapny" style="width:300px;height:300px;"-->
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
</div></div>
	
    <div class="col">
	<div class="flip-card">
  <div class="flip-card-inner">
    <div class="flip-card-front">
      <img src="image?image=payroll-800x800.png" alt="Payroll" style="width:300px;height:300px;">
	  <!--img src="payroll-800x800.png" alt="Payroll" style="width:300px;height:300px;"-->
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
</div></div>
	
    <div class="col">
	<div class="flip-card">
  <div class="flip-card-inner">
    <div class="flip-card-front">
      <img src="image?image=warehouse.jpg" alt="Warehouse" style="width:300px;height:300px;">
	  <!-- img src="warehouse.jpg" alt="Warehouse" style="width:300px;height:300px;" -->
    </div>
    <div class="flip-card-back">
      <h1>Warehouse</h1> 
      <p>Manage the warehouse</p> 
      <p>Store all item details in the warehouse</p>
      <div align="center">
		<a href="payroll" class="btn btn-info" style="margin-top:20px;" role="button">Open Warehouse</a>
	</div>
    </div>
  </div>
</div></div>
	
  </div>
  <!--/div-->
  <br>
  <br>
  
  
  
  <br>
  
	<BR><BR>
  <div style="background-color:black;">

		<h1 class="text-white">&nbsp; WEB BASED ERP</H1>
		<BR>
		&nbsp; <img src="WERP.PNG" width="280" height="70">
		<BR><BR>
		<P class="text-white">&nbsp; CONTACT US:</P>
		<P class="text-white">&nbsp; Nishant Sharma Mob.: 9999999999 Email: 4unishant@gmail.com</P>
		<P class="text-white">&nbsp; Rohan Gupta Mob.: 9650471132 Email: rohangupta0247@gmail.com</P>
		<P class="text-white">&nbsp; Rohit Kumar Mob.: 9999999999 Email: rohitkumar@gmail.com</P>
  </div>

</div>





<jsp:include page="footer.jsp" />
