<!DOCTYPE html>
<html lang="en">
<head>
  <title>ERP</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
  <style>
  .fakeimg {
    height: 200px;
    background: #aaa;
  }
  </style>
</head>
<body>
<%@page session="false" %>
<%
HttpSession session= request.getSession(false);
if(session==null)
	out.print("<a href=\"login\">Login</a><br>");
else
	out.print(session.getAttribute("name")+"'s ERP"+
	"<br><a href=\"logout\">Logout</a><br>");
%>
<div class="jumbotron text-center" style="margin-bottom:0">
  <h1>ERP</h1>
  <p>( Resize this responsive page to see the effect! )</p> 
</div>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <a class="navbar-brand" href="#">Menu</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="../save">Company</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Warehouse</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Inventory</a>
      </li>    
    </ul>
  </div>  
</nav>

<div class="container" style="margin-top:30px">
  <div class="row">
    <div class="col-sm-4">
      <h2>About ERP</h2>
      <h5>Photo of me:</h5>
      <div class="fakeimg">Fake Image</div>
      <p>
      This is a web based ERP
      </p>
      
      <!--h3>See also: </h3>
      <p>Lorem ipsum dolor sit ame.</p>
      <ul class="nav nav-pills flex-column">
        <li class="nav-item">
          <a class="nav-link active" href="#">Active</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled" href="#">Disabled</a>
        </li>
      </ul-->
      <hr class="d-sm-none">
    </div>
    <div class="col-sm-8">
      <h2>WELCOME TO ERP</h2>
      <h5>Web-based ERP</h5>
      <div class="fakeimg">Fake Image</div>
      <p>
         Enterprise Resource Planning (ERP) is the integrated management of core business processes, often in real-time and mediated by software and technology.
      </p>
      <br>
      
      <!--h2>TITLE HEADING</h2>
      <h5>Title description, Sep 2, 2017</h5>
      <div class="fakeimg">Fake Image</div>
      <p>Some text..</p>
      <p>
      Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.
      </p-->
    </div>
  </div>
</div>

<div class="jumbotron text-center" style="margin-bottom:0">
  <p>ERP footer</p>
</div>

</body>
</html>
