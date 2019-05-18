<jsp:include page="../header.jsp" />

<%@page isELIgnored="false"%>
<% Boolean loginStatus= (Boolean)pageContext.getAttribute("loginStatus", PageContext.REQUEST_SCOPE); 
if( loginStatus!=null && loginStatus==false){
		request.getSession(false).invalidate();
		//in this case we dont want further excecutions
		return;
}%>
		
<%
String query= request.getParameter("query");
String querytoNamingCaseFromCamelCase= (String)request.getAttribute("querytoNamingCaseFromCamelCase");
int numberOfRecords= (Integer)request.getAttribute("numberOfRecords");
int pageNumber= (Integer)request.getAttribute("pageNumber");
int recordsPerPage= (Integer)request.getAttribute("recordsPerPage");
int paginationSize= (Integer)request.getAttribute("paginationSize");
String search= (String)request.getAttribute("search");
String searchItem= (String)request.getAttribute("searchItem");
String columns[] = (String[])request.getAttribute("columns");
String columnstoNamingCase[] = (String[])request.getAttribute("columnstoNamingCase");
String row[][] = (String[][])request.getAttribute("row");
String idSeparator = (String)request.getAttribute("idSeparator");
%>

	<div class="container">
	
	<h3 class="text-center" style="padding-bottom:20px;"> <%= querytoNamingCaseFromCamelCase%> </h3>

	<% if(numberOfRecords==0){ %>
	<h5 class="text-center" style="padding-bottom:20px;">
		No <%=querytoNamingCaseFromCamelCase%> found. Click on ADD to add new record.
		<br>
		<a href="save?query=${param.query}&status=add" class="btn btn-primary" style="margin-top:20px;" role="button">ADD</a>
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
				<a class="dropdown-item" id="<%=columns[i]%>" onclick="searchInputFunction(this.id)"><%= columnstoNamingCase[i]%></a>
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
			<a href="save?query=<%=request.getParameter("query") %>&status=add" class="btn btn-primary" role="button">ADD</a>
			<!--button type="button" class="btn btn-primary">ADD</button>
			<button type="button" class="btn btn-warning">UPDATE</button>
			<button type="button" class="btn btn-danger">DELETE</button-->
		</div>
	
	<%if(search!=null && !search.equals("") && searchItem!=null && !searchItem.equals("")) { %> 
	<h5 class="text-center" style="padding-bottom:20px;"> Search results for <%= searchItem %> </h5>
	<% } %>
		
		<%
					  %>




<style>
ul, #myUL {
  list-style-type: none;
}

#myUL {
  margin: 0;
  padding: 0;
}

.caret {
  cursor: pointer;
  -webkit-user-select: none; /* Safari 3.1+ */
  -moz-user-select: none; /* Firefox 2+ */
  -ms-user-select: none; /* IE 10+ */
  user-select: none;
}

.caret::before {
  content: "\25B6";
  color: black;
  display: inline-block;
  margin-right: 6px;
}

.caret-down::before {
  -ms-transform: rotate(90deg); /* IE 9 */
  -webkit-transform: rotate(90deg); /* Safari */'
  transform: rotate(90deg);  
}

.nested {
  display: none;
}

.active {
  display: block;
}
</style>


<div class="modal fade" id="myModal">
<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
		<h4 class="modal-title">DETAILS</h4>
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
      
    </div>
    <div class="modal-body">
      
<ul id="myUL">
  <!--li><span class="caret" onclick="toggleCaret(this)" id="topSpan"></span>
    <ul class="nested">
      <li>ITEM ID: 6</li>
      <li>ITEM NAME: ab</li>
      <li><span class="caret">WAREHOUSE</span>
        <ul class="nested">
          <li>WAREHOUSE NAME: S</li>
          <li>PHONE: 987654</li>
          <li><span class="caret">WAREHOUSE ADDRESS: 9</span>
            <ul class="nested">
              <li>AREA: POCHINKI</li>
              <li>DISTRICT: THANE</li>
              <li>STATE: ERANGEL</li>
              <li>COUNTRY: PUBG</li>
            </ul>
          </li>
		  <li>PINCODE: 111111</li>
        </ul>
      </li>
		<li>QUALITY: 12</li>
		<li>COST: 1200</li>
    </ul>
  </li-->
</ul>
	  
    </div>
    <div class="modal-footer">
      <a id="updateModal" href="#" class="btn btn-warning" role="button">Update</a>
      <!--a id="deleteModal" href="" class="btn btn-danger" role="button">Delete</a-->
      <button type="button"  id="deleteModal" onclick="" class="btn btn-danger">Delete</button>
      <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
    </div>
  </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->



		
		<%if(row.length==0) {%>
		<h5 class="text-center" style="padding-bottom:20px;"> No search results found.</h5>
		<% } else { %>
		<div class="table-responsive">
		(Click on a record to view it's details)
			<table class="table table-hover" id="allDataTable">
				<thead class="thead-dark">
					<tr>
						<th onclick="sortTable(0)"><%= "ID"%></th>
						<%for(int i=0; i<columnstoNamingCase.length; i++){ %>
						<th onclick="sortTable(<%= i+1 %>)"><%= columnstoNamingCase[i]%></th>
						<%} %>
					</tr>
				</thead>

				<tbody>
					<%  for(int i=0; i<row.length; i++){ %>
				<tr>
					<%for(int j=0; j<row[i].length; j++){ 
					%>
					<td>
					<%if(row[i][j].startsWith("[") && row[i][j].endsWith("]")){
						row[i][j]=row[i][j].substring(1,row[i][j].length()-1);
					}
					%>
					<%=row[i][j] %>
					</td>
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
						href="view?query=<%=request.getParameter("query")%>&page=<%=i%>"><%=i%></a></li>
					<% } %>
					<%if(paginationSize>1){ %>
					<!--li class="page-item"><a class="page-link" href="#">></a></li-->
					<% } %>
				</ul>
			</nav>
		</div>
		<%} %>

	<%}%>
	
	</div>
	

	
<style>

th , td {
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
<script>

function toggleCaret(thisSpan) {
	 var thisLi= thisSpan.parentElement;
	  var str= thisSpan.innerHTML.split(" # "); 
	 
	 var temp=str[0].split(" ");
	 var queryToCamelCase="";
	 for(var k=0;k<temp.length;k++)
	 	queryToCamelCase += temp[k]; 
	 if(thisSpan.className=="caret" && thisLi.children.length==1){
		 //alert('going');
	  var xmlhttp = new XMLHttpRequest();
	  xmlhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      myFunction(this, thisLi);
	    }
	  };
	  xmlhttp.open("GET", "getEntityInJSArray?query="+queryToCamelCase+"&id="+str[1], true);
	  xmlhttp.send();
	 }
	 else{
		    thisSpan.parentElement.querySelector(".nested").classList.toggle("active");
		    thisSpan.classList.toggle("caret-down");
	 }
	function myFunction(response, thisLi) {
	  var i;
	  var entityJson = JSON.parse(response.responseText);
	  var entity = entityJson.entity;
	  var attrs= entityJson.attrs;
	  var ul=document.createElement("ul");
	  ul.className="nested";
	  for(var k=0;k<attrs.length;k++){
	  	  if(Array.isArray(entity[k])){
	  		//ul.innerHTML+= "<li>"+attrs[k]+" : ";---->> problem, as after line "</li>" is auto inserted
	  		var tempHTML="";
	  		tempHTML+= attrs[k]+" : ";
	  		  if(entity[k].length>0){
	  			tempHTML+= entity[k][0];
	  			for(var l=1;l<entity[k].length;l++)	  			
	  				tempHTML+= ", "+entity[k][l];
	  		  }
	  		  else{
	  			tempHTML+= "-";
	  		  }
	  		ul.innerHTML+= "<li>"+tempHTML+"</li>";
	  	  }
		  else if(!entity[k].startsWith("<%= idSeparator%>"))
		  	ul.innerHTML+= "<li>"+attrs[k]+" : "+entity[k]+"</li>";
		else
			ul.innerHTML+= "<li><span class=\"caret\" onclick=\"toggleCaret(this)\">"+attrs[k]+" # "+entity[k].split("#")[2]+"</span></li>";
	  }
	  thisLi.appendChild(ul);
	  
    thisSpan.parentElement.querySelector(".nested").classList.toggle("active");
    thisSpan.classList.toggle("caret-down");
	}
  }
  
  

function deleteCall(id) {
	 //alert('going');
	  var xmlhttp = new XMLHttpRequest();
	  xmlhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      mydeleteFunction(this);
	    }
	  };
	  xmlhttp.open("GET", "deleteEntity?query=<%=query%>&id="+id, true);
	  xmlhttp.send();
	 
	function mydeleteFunction(response) {
	  var i;
	  var status = JSON.parse(response.responseText).deleted;
		if(status==true)
			location.reload();
		else if(status==false)
			alert('This record can not to be deleted as this record is being used somewhere else.');
	}
 }




var table = document.getElementById("allDataTable");
var rows = table.getElementsByTagName("tr");
for (i = 0; i < rows.length; i++) {
  var currentRow = table.rows[i];
  var createClickHandler = function(row) {
    return function() {
      var cell = row.getElementsByTagName("td")[0];
      var id = cell.innerHTML;
      
      
      //alert(id);      
      document.getElementById("myUL").innerHTML="<li><span class=\"caret\" onclick=\"toggleCaret(this)\" id=\"topSpan\"><%= querytoNamingCaseFromCamelCase%> # "+id+"</span>";
      document.getElementById("updateModal").href="save?query=<%=request.getParameter("query") %>&status=update&id="+id;
      //document.getElementById("deleteModal").onclick=deleteCall(id);
      document.getElementById( "deleteModal" ).setAttribute( "onClick", "javascript: deleteCall("+id+");" );
      
      $('#myModal').modal('show');
    };
  };
  currentRow.onclick = createClickHandler(currentRow);
}


</script>

	
<jsp:include page="../footer.jsp" />