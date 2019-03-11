<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<!-- Bootstrap CSS -->
<!-- <link rel="stylesheet" href="bootstrap.min.css"> -->
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- <link rel="stylesheet" type="text/css" href="bootstrap.css"> -->

<!-- Bootstrap CSS>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous"-->


<!-- Bootstrap 4 Modal-->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</head>
<body>

<%@page import="com.saptris.erp.UserManager"%>
<%@page session="false"%>
<%
	HttpSession session= UserManager.getSession(request);
	String from= request.getRequestURI()+"?"+request.getQueryString();
	String loginRedirection= "../login?from="+from;
	if(session==null){
		//redirection does not work in case of jsp:include
		//response.sendRedirect(loginRedirection);
		
		//so creating new session that needs to be ended on calling page
		session= request.getSession();
		pageContext.setAttribute("loginStatus", false, PageContext.REQUEST_SCOPE);
		%>
		
		<script type="text/javascript">
    	$(window).on('load',function(){
    		$('#loginModal').modal({backdrop: 'static', keyboard: false}, 'show');
    	});
		</script>
		
		<%}
	else {
		out.print(session.getAttribute("name")+"'s ERP"+"<br><a href=\"../logout\">Logout</a><br>");
	}
%>

<div class="jumbotron text-center" style="margin-bottom: 0">
		<h1>ERP</h1>
		<!--button type="button" class="btn btn-primary" data-toggle="modal" data-backdrop="static" data-keyboard="false" data-target="#loginModal">
  			Login modal
		</button-->
<div class="modal" id="loginModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Login first</h4>
        <!--button type="button" class="close" data-dismiss="modal">&times;</button-->
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        You are currently not logged in. Please login first
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <a href="<%= loginRedirection %>" class="btn btn-primary" role="button">Login</a>
        <!--button type="button" class="btn btn-primary" data-dismiss="modal" data-target="../login?from="+from">Login</button-->
        <!--button type="button" class="btn btn-danger" data-dismiss="modal">Close</button-->
      </div>

    </div>
  </div>
</div>
	</div>

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark sticky-top" style="margin-bottom:25px;">
		<a class="navbar-brand" href="#">Menu</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
			<%String currentPage= "";
			if(request.getRequestURI().endsWith("view"))
				currentPage= "";
			else
				currentPage= "../view";
				%>
				<li class="nav-item"><a class="nav-link"
					href="<%= currentPage %>?query=Company">Company</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%= currentPage %>?query=Warehouse">Warehouse</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%= currentPage %>?query=Item">Inventory</a></li>
			</ul>
		</div>
	</nav>
