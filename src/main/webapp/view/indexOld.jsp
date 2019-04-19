<jsp:include page="../header.jsp" />

<%@page import="com.saptris.erp.EntityManager"%>
<% Boolean loginStatus= (Boolean)pageContext.getAttribute("loginStatus", PageContext.REQUEST_SCOPE); 
if( loginStatus!=null && loginStatus==false){
		request.getSession(false).invalidate();
		//in this case we dont want further excecutions
		return;
}%>
		
<%
// number of records to display per page
int recordsPerPage= 10;

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

String search= request.getParameter("search"); 
String searchItem= request.getParameter("searchquery");
%>

	<div class="container">
	
	<h3 class="text-center" style="padding-bottom:20px;"> <%=EntityManager.toNamingCaseFromCamelCase(query) %> </h3>

	<%if(numberOfRecords==0){ %>		
	<h5 class="text-center" style="padding-bottom:20px;">
		No <%=EntityManager.toNamingCaseFromCamelCase(query) %> found. Click on ADD to add new record.
		<br>
		<a href="save?query=<%=request.getParameter("query") %>&status=add" class="btn btn-primary" style="margin-top:20px;" role="button">ADD</a>
	</h5>
	
	<% } else {%>

<!-- required for dropdown of search -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

		<div class="input-group input-group-lg" style="padding-bottom:20px;">
			<span class="input-group-addon" id="addon1"></span>
			<input id="searchInput" type="text" class="form-control" placeholder="SEARCH" aria-describedby="addon1" 
			<% if(searchItem!=null && !searchItem.equals("")) {%>
				value="<%= searchItem %>">
			<% } %>
			</input>
			<div class="input-group-append">
			<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">Search for...</button>
			<!--div class="dropdown"-->
			<div class="dropdown-menu">
				<!--a class="dropdown-item" href="#">ID</a-->
				<%for(int i=0; i<columns.length; i++){ %>
				<a class="dropdown-item" id="<%=columns[i]%>" onclick="searchInputFunction(this.id)"><%= EntityManager.toNamingCase(columns[i])%></a>
				<%} %>
				<script type="text/javascript">
				function searchInputFunction(id) {
					window.open( "?query=<%=query %>&search=" + id + "&searchquery=" + document.getElementById("searchInput").value , "_top" ) ;
				}
				</script>
			</div>
			<!--/div-->
			</div>
		</div>

		<div class="text-center" style="padding-bottom:20px;">
			<a href="../save?query=<%=request.getParameter("query") %>&status=add" class="btn btn-primary" role="button">ADD</a>
			<!--button type="button" class="btn btn-primary">ADD</button>
			<button type="button" class="btn btn-warning">UPDATE</button>
			<button type="button" class="btn btn-danger">DELETE</button-->
		</div>
	
	<%if(search!=null && !search.equals("") && searchItem!=null && !searchItem.equals("")) { %> 
	<h5 class="text-center" style="padding-bottom:20px;"> Search results for <%= searchItem %> </h5>
	<% } %>
		
		<%
					  int count= recordsPerPage;
					  int start= (pageNumber-1)*recordsPerPage+1;
					  int diff= numberOfRecords-start;
					  if(diff<recordsPerPage)
						  count= diff+1;
					  
					  if(search==null || search.equals("")){
					  	  row= entityManager.getAllEntityString(count, start);
					  }
					  else{
						  if(searchItem==null || searchItem.equals(""))
					  	  	  row= entityManager.getAllEntityString(count, start);
						  else {
						  	  paginationSize=0;
						      row= entityManager.search(search, searchItem);
					  	  }
					  } %>
		
		<%if(row.length==0) {%>
		<h5 class="text-center" style="padding-bottom:20px;"> No search results found.</h5>
		<% } else { %>
		<div class="table-responsive">
			<table class="table table-hover" id="allDataTable">
				<thead class="thead-dark">
					<tr>
						<th onclick="sortTable(0)"><%= "ID"%></th>
						<%for(int i=0; i<columns.length; i++){ %>
						<th onclick="sortTable(<%= i+1 %>)"><%= EntityManager.toNamingCase(columns[i])%></th>
						<%} %>
					</tr>
				</thead>

				<tbody>
					<%  for(int i=0; i<row.length; i++){ %>
				<tr>
					<%for(int j=0; j<row[i].length; j++){ 
						  if(row[i][j].startsWith(EntityManager.idSeparator))
							  //seprator#ClassName#id
							  row[i][j]= row[i][j].split("#")[2];
					%>
					<td><%= row[i][j]%></td>
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
					<%if(paginationSize>1){ %>
					<!-- li class="page-item"><a class="page-link" href="#"><</a></li-->
					<% } %>
					<%for(int i=1; i<=paginationSize; i++) { %>
					<li class="page-item"><a class="page-link"
						href="?query=<%=request.getParameter("query")%>&page=<%=i%>"><%=i%></a></li>
					<% } %>
					<%if(paginationSize>1){ %>
					<!--li class="page-item"><a class="page-link" href="#">></a></li-->
					<% } %>
				</ul>
			</nav>
		</div>
		<%} %>

	<%} %>
	
	</div>
	
	<style>

th {
  cursor: pointer;
}
</style>
	
	
	<script>
function sortTable(n) {
  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
  table = document.getElementById("allDataTable");
  switching = true;
  //by default sorting direction to ascending
  dir = "asc"; 
  //Make a loop that will continue until no switching has been done
  while (switching) {
    //start by saying: no switching is done:
    switching = false;
    rows = table.rows;
    //Loop through all table rows (except the first, which contains table headers):
    for (i = 1; i < (rows.length - 1); i++) {
      //start by saying there should be no switching:
      shouldSwitch = false;
      //Get the two elements you want to compare, one from current row and one from the next:
      x = rows[i].getElementsByTagName("TD")[n];
      y = rows[i + 1].getElementsByTagName("TD")[n];
      //check if the two rows should switch place, based on the direction, asc or desc:
      if (dir == "asc") {
        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
          //if so, mark as a switch and break the loop:
          shouldSwitch= true;
          break;
        }
      } else if (dir == "desc") {
        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
          //if so, mark as a switch and break the loop:
          shouldSwitch = true;
          break;
        }
      }
    }
    if (shouldSwitch) {
      //If a switch has been marked, make the switch and mark that a switch has been done:
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
      //Each time a switch is done, increase this count by 1:
      switchcount ++;      
    } else {
      //If no switching has been done AND the direction is "asc", set the direction to "desc" and run the while loop again.
      if (switchcount == 0 && dir == "asc") {
        dir = "desc";
        switching = true;
      }
    }
  }
}
</script>
	
<jsp:include page="../footer.jsp" />