<!doctype html>
<%@page import="com.saptris.erp.EntityManager"%>
<html lang="en">
<head>

<jsp:include page="../header.jsp" />
<% Boolean loginStatus= (Boolean)pageContext.getAttribute("loginStatus", PageContext.REQUEST_SCOPE); 
if( loginStatus!=null && loginStatus==false){
		request.getSession(false).invalidate();
		//in this case we dont want further excecutions
		return;
}%>
		
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

	<div class="container">
	
	<h3 class="text-center" style="padding-bottom:20px;"> <%=EntityManager.toNamingCase(query) %> </h3>

		<div class="input-group input-group-lg" style="padding-bottom:20px;">
			<span class="input-group-addon" id="addon1"></span> <input
				type="text" class="form-control" placeholder="SEARCH"
				aria-describedby="addon1"></input>
			<button type="button" class="btn btn-success">SEARCH</button>
		</div>

		<div class="text-center" style="padding-bottom:20px;">
			<a href="../save?query=<%=request.getParameter("query") %>&status=add" class="btn btn-primary" role="button">ADD</a>
			<!--button type="button" class="btn btn-primary">ADD</button-->
			<button type="button" class="btn btn-info">UPDATE</button>
			<button type="button" class="btn btn-danger">DELETE</button>
		</div>
		
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
<jsp:include page="../footer.jsp" />