<!doctype html>
<%@page import="com.saptris.erp.UserManager"%>
<%@page import="com.saptris.erp.EntityManager"%>
<html lang="en">
<head>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<!-- Bootstrap CSS -->
<!-- <link rel="stylesheet" href="bootstrap.min.css"> -->
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<title>MAJOR PROJECT</title>
<!-- <link rel="stylesheet" type="text/css" href="bootstrap.css"> -->

</head>
<body>
	<%@page session="false"%>
	<%
HttpSession session= UserManager.getSession(request);
String from= request.getRequestURI()+"?"+request.getQueryString();
if(session==null){
	out.print("<a href=\"login?from="+from+">Login</a><br>");
	response.sendRedirect("login?from="+from);
	//in this case we dont want further excecutions
	return;
}
else
	out.print(session.getAttribute("name")+"'s ERP"+
	"<br><a href=\"logout\">Logout</a><br>");
%>
	<%
// number of records to display per page
int recordsPerPage= 2;

//default page
String defaultPage="Company";
	
if(recordsPerPage<1){
	out.print("Error : Showing 0 records on every page");
	return;
}
int pageNumber= 0;
String pageString= request.getParameter("page");
if(pageString==null)
	pageNumber=1;
else
	pageNumber= Integer.parseInt(pageString);

String query= request.getParameter("query");
if(query==null){
	/*response.sendRedirect("..");
	return;*/
	
	//default page is of company now
	query=defaultPage;
}
EntityManager entityManager= new EntityManager(query);
int numberOfRecords= entityManager.getCount();
int paginationSize= numberOfRecords/recordsPerPage;
paginationSize= (numberOfRecords%recordsPerPage==0) ? paginationSize : (paginationSize+1) ;
String[] columns =entityManager.getAttributesName();
String[][] row;
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
					href="?query=Company">Company</a></li>
				<li class="nav-item"><a class="nav-link"
					href="?query=Warehouse">Warehouse</a></li>
				<li class="nav-item"><a class="nav-link"
					href="?query=Item">Inventory</a></li>
			</ul>
		</div>
	</nav>


	<div class="container">

		</br>

		<div class="input-group input-group-lg">
			<span class="input-group-addon" id="addon1"></span> <input
				type="text" class="form-control" placeholder="SEARCH"
				aria-describedby="addon1"></input>
			<button type="button" class="btn btn-success">SEARCH</button>
		</div>
		</br>


		<div class="text-center">
			<a href="../save?query=<%=request.getParameter("query") %>&status=add" class="btn btn-primary" role="button">ADD</a>
			<!--button type="button" class="btn btn-primary">ADD</button-->
			<button type="button" class="btn btn-info">UPDATE</button>
			<button type="button" class="btn btn-danger">DELETE</button>
		</div>
		</br>
		
		<div class="table-responsive">
			<table class="table table-hover">
				<thead class="thead-dark">
					<tr>
						<th><%= "ID"%></th>
						<%for(int i=0; i<columns.length; i++){ %>
						<th><%= EntityManager.toNamingCase(columns[i])%></th>
						<%} %>
						<!--th>Firstname</th>
						<th>Lastname</th>
						<th>Email</th-->
					</tr>
				</thead>

				<tbody>
				<%
					  int count= recordsPerPage;
					  int start= (pageNumber-1)*recordsPerPage+1;
					  int diff= numberOfRecords-start;
					  if(diff<recordsPerPage)
						  count= diff+1;
					  row= entityManager.getAllEntityString(count, start);
					  for(int i=0; i<row.length; i++){ %>
				<tr>
					<%for(int j=0; j<row[i].length; j++){ 
						  if(row[i][j].startsWith(EntityManager.idSeparator))
							  //seprator#ClassName#id
							  row[i][j]= row[i][j].split("#")[2];
					%>
					<th><%= row[i][j]%></th>
					<%} %>
				</tr>
				<%} %>

				<!--tr>
					<th>MyAbc</th>
					<th>Cba</th>
					<th>a@b.c</th>
				</tr>
				<tr>
					<th>Def</th>
					<th>Fed</th>
					<th>d@e.f</th>
				</tr-->
				
				</tbody>
			</table>
		</div>

		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
			<br />

			<nav>
				<ul class="pagination">
					<!-- li class="page-item"><a class="page-link" href="#"><</a></li-->
					<%for(int i=1; i<=paginationSize; i++) { %>
					<li class="page-item"><a class="page-link"
						href="?query=<%=request.getParameter("query")%>&page=<%=i%>"><%=i%></a></li>
					<% } %>
					<!--li class="page-item"><a class="page-link" href="#">></a></li-->
				</ul>
			</nav>
		</div>


	</div>

	<div class="jumbotron text-center" style="margin-bottom: 0">
		<p>ERP</p>
	</div>


	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<!-- <script src="jquery-3.3.1.slim.min.js"></script>
    <script src="popper.min.js"></script>
    <script src="bootstrap.min.js"></script>  -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	<script
		src="https://cdn.rawgit.com/twbs/bootstrap/v4-dev/dist/js/bootstrap.js"></script>
</body>
</html>