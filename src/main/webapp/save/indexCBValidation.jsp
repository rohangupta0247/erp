<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="com.saptris.erp.UserManager"%>
<jsp:include page="../header.jsp" />
<%@page import="java.util.List"%>
<%--@page errorPage="../error500.jsp"--%>
<% Boolean loginStatus= (Boolean)pageContext.getAttribute("loginStatus", PageContext.REQUEST_SCOPE); 
if( loginStatus!=null && loginStatus==false){
		request.getSession(false).invalidate();
		//in this case we dont want further excecutions
		return;
}%>

<%
String query= request.getParameter("query");
String querytoNamingCaseFromCamelCase= (String)request.getAttribute("querytoNamingCaseFromCamelCase");
String status= (String)request.getAttribute("status");
String dateFormat= (String)request.getAttribute("dateFormat");
String datetimeFormat= (String)request.getAttribute("datetimeFormat");
String attributes[]= (String[])request.getAttribute("attributes");
String attributestoNamingCase[]= (String[])request.getAttribute("attributestoNamingCase");
String attributestoRunningCase[]= (String[])request.getAttribute("attributestoRunningCase");
String attributestoRunningSpaceCase[]= (String[])request.getAttribute("attributestoRunningSpaceCase");
String types[]= (String[])request.getAttribute("types");
Boolean nullable[]= (Boolean[])request.getAttribute("nullable");
Map<String, Map<String,String>> dynamicDropdown= (Map<String, Map<String,String>>)request.getAttribute("dynamicDropdown");
%>

    <div class="container">
    
    <h3 class="text-center" style="padding-bottom:20px;">
    <%if(status.equals("add")) {%>
    <%="Add New" %>
    <%} else {%>
    <%="Update" %>
    <%} %>
     <%=querytoNamingCaseFromCamelCase %> </h3>


<style>
.dropbtn {
	background-color: #4CAF50;
	color: white;
	padding: 16px;
	font-size: 16px;
	border: none;
	cursor: pointer;
}

.dropbtn:hover, .dropbtn:focus {
	background-color: #3e8e41;
}

#myInput {
	border-box: box-sizing;
	background-image: url('image?image=searchicon.png');
	background-position: 14px 12px;
	background-repeat: no-repeat;
	font-size: 16px;
	padding: 14px 20px 12px 45px;
	border: none;
	border-bottom: 1px solid #ddd;
}

#myInput:focus {
	outline: 3px solid #ddd;
}

.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f6f6f6;
	min-width: 230px;
	overflow: auto;
	border: 1px solid #ddd;
	z-index: 1;
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

.dropdown a:hover {
	background-color: #ddd;
}

.show {
	display: block;
}
</style>


     
<script>
//validation required because Firefox and Safari don't support datetime-local input type
function validateForm() {
  var form = document.forms[0].elements;
  for(var i=0; i<form.length;i++){
    var ele = document.forms[0].elements[i];
    if(ele.placeholder=="<%=dateFormat%>" && ele.hasAttribute("required")){
  	  var x= ele.value;
      if (x.search(/\d\d\d\d-\d\d-\d\d/)) {
        alert("Date must be filled with format:- YYYY-MM-DD");
        return false;
      }
    }
    else if(ele.placeholder=="<%=datetimeFormat%>"){
  	  var x= ele.value;
  	  if(x.search(/\d\d\d\d-\d\d-\d\dT\d\d:\d\d/)==0) {
      	return true;
      }
      else if (x.search(/\d\d\d\d-\d\d-\d\d\/\d\d:\d\d\s(a|p)m/i)) {
    	  //search() returns -1 in case string not mathces and will show alert
        alert("Date must be filled with format:- YYYY-MM-DD/hh:mm am/pm. For example:- 2001-01-01/01:00 am");
        return false;
      }
      else{
    	  if(x.charAt(17)=='a' || x.charAt(17)=='A'){
    		  if(x.charAt(11)=='1' && x.charAt(12)=='2'){
    			  x=x.replace(/\d\d:/,'00:');
    		  }
    	  }
    	  else if(x.charAt(17)=='p' || x.charAt(17)=='P'){
    		  var temp= '' + x.charAt(11) + x.charAt(12);
    		  temp= Number(temp);
    		  if(temp!=12)
    			  temp+= 12;
    		  x=x.replace(/\d\d:/,temp.toString()+":");
    	  }
    	  x=x.replace("/","T");
    	  x=x.replace(/\s(a|p)m/i,"");
    	  document.forms[0].elements[i].value=x;
      }
    }
  }
  

<% 
int addMoreCount=0;
int c=0;
for(c=0; c<types.length;c++){
	if(types[c].equals("add-more"))
		addMoreCount++;
}
c=0;
%>
  
  
  var cbCount= <%= addMoreCount%>;
  var z;
  for(z=0;z<cbCount;z++){
  var check = document.getElementsByClassName("my-checkbox"+z);
  var flag, i;
  for (i = 0; i < a.length; i++) {
    flag=a[i].checked;
    if(flag==true)
    	break;
  }
  if(flag==false){
  	var hiddenField= document.createElement('input');
    hiddenField.type='hidden';
    hiddenField.name='';
    hiddenField.value='';
    form.appendChild(hiddenField);
  }
}
}



/* When the user clicks on the button,
toggle between hiding and showing the dropdown content */
function myFunction() {
  document.getElementById("myDropdown").classList.toggle("show");
}

function filterFunction() {
  var input, filter, ul, li, a, i;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  div = document.getElementById("myDropdown");
  //a = div.getElementsByTagName("a");
  //a = div.getElementsByTagName("option");
  a = div.getElementsByClassName("form-check");
  for (i = 0; i < a.length; i++) {
    txtValue = a[i].textContent || a[i].innerText;
    if (txtValue.toUpperCase().indexOf(filter) > -1) {
      a[i].style.display = "";
    } else {
      a[i].style.display = "none";
    }
  }
}
</script>
     
 <%/* %> <form action="success.jsp?query=<%=query %>&status=<%=status %>" method="POST" name="form" onsubmit="return validateForm()"> <%*/ %>
  <!--form action="saveRecord?query=<%=query %>&status=<%=status %>" method="POST" name="form" onsubmit="return validateForm()"-->
  <form action="https://www.w3schools.com/action_page.php" method="GET" name="form" onsubmit="return validateForm()">
  
  
  <%for(int i=0; i<attributes.length; i++){ 
  	String required= "required";
	  if(nullable[i])
  		required="";
	String autofocus="";
	  if(i==0)
		  autofocus="autofocus";
  %>
  <div class="row form-group">
      <div class="col-sm-4">
        <label><%= attributestoNamingCase[i] %></label>
      </div>
	  
      <div class="col-sm-8">
  <% 
	//Done fixme task: user is null exception while refresh : by adding login modal
	
	//XXX input types in this method, getAttributesType and saveRecord method of EntityManager
	if(types[i].equals("text")){%>
		<input <%= required %> <%= autofocus %> type="text" placeholder="enter <%= attributestoRunningSpaceCase[i] %> here" class="form-control" name="<%= attributestoRunningCase[i] %>" id="<%= attributestoRunningCase[i] %>">
	<% }
	else if(types[i].equals("number")){%>
	<input <%= required %> <%= autofocus %> type="number" placeholder="enter <%= attributestoRunningSpaceCase[i] %> here" class="form-control" name="<%= attributestoRunningCase[i] %>" id="<%= attributestoRunningCase[i] %>">
<% }
	else if(types[i].equals("numberAnyStep")){%>
	<input <%= required %> <%= autofocus %> type="number" step="any" placeholder="enter <%= attributestoRunningSpaceCase[i] %> here" class="form-control" name="<%= attributestoRunningCase[i] %>" id="<%= attributestoRunningCase[i] %>">
<% }
	else if(types[i].equals("date")){%>
	<input <%= required %> <%= autofocus %> type="date" placeholder="<%=dateFormat%>" class="form-control" name="<%= attributestoRunningCase[i] %>" id="<%= attributestoRunningCase[i] %>">
<% }
	else if(types[i].equals("datetime")){%>
	<input <%= required %> <%= autofocus %> type="datetime-local" placeholder="<%=datetimeFormat%>" class="form-control" name="<%= attributestoRunningCase[i] %>" id="<%= attributestoRunningCase[i] %>">
<% }
	else if(types[i].equals("select") || types[i].equals("options")){
		
		Map<String, String> temp= dynamicDropdown.get(attributes[i]);
		String optionValuetoRunningCase, optionShow;
		%>
		<select <%= required %> <%= autofocus %> class="form-control" name="<%= attributestoRunningCase[i] %>" id="<%= attributestoRunningCase[i] %>">
		<option value="">--select--</option>
		<%
		//for(int j=0;j<foreignValues.size();j++){
		for(Entry<String,String> entry: temp.entrySet()){
			optionValuetoRunningCase= entry.getKey();
			optionShow= entry.getValue();
			%>
			<option value="<%= optionValuetoRunningCase %>"><%= optionShow %></option>
		<% } %>
		</select>
	<% }
	   else if(types[i].equals("add-more")){ 
	   //TODO required and autofocus attribute for checkbox
	   int f=1;
	   
	   Map<String, String> temp= dynamicDropdown.get(attributes[i]);
		String optionValuetoRunningCase, optionShow;
		%>
		<div class="dropdown">
			<button type="button" onclick="myFunction()" class="dropbtn">Dropdown</button>
			<div id="myDropdown" class="dropdown-content">
				<input type="text" placeholder="Search.." id="myInput"
					onkeyup="filterFunction()">
					
		<%
		if(temp.size()==0){%>
		        <input type="hidden" id="no<%= attributestoRunningCase[i] %>" name="<%= attributestoRunningCase[i] %>" value="">
		<%}
		
		//for(int j=0;j<foreignValues.size();j++){
		for(Entry<String,String> entry: temp.entrySet()){
			optionValuetoRunningCase= entry.getKey();
			optionShow= entry.getValue();
			%>
			<div class="form-check">
		      <label class="form-check-label" for="<%= attributestoRunningCase[i] + f%>">
		        <input type="checkbox" class="form-check-input" id="<%= attributestoRunningCase[i] + f++%>" name="<%= attributestoRunningCase[i] %>" value="<%= optionValuetoRunningCase %>"><%= optionShow %>
		      </label>
		    </div>
		<% } %>
			</div>
		</div>
	  <% }
  		else if(types[i].equals("user")){ %>
  		<input readonly value="<%= UserManager.getUser().getUsername() %>" <%= required %> <%= autofocus %> type="text" placeholder="enter <%= attributestoRunningSpaceCase[i] %> here" class="form-control" name="<%= attributestoRunningCase[i] %>">
  		<%} %>
	</div>
    </div>
<%} %>
  
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->  
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->  
<!--script>
document.getElementById('itemname').value='lift';
document.getElementById('maintenancetime').value='2019-03-21T00:21';
document.getElementById('description').value='lift emc';
document.getElementById('maintainername').value='nishant';
document.getElementById('maintaineremail').value='rohangp227@gmail.com';
document.getElementById('maintainerphone').value='9650471132';
</script-->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->  
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->  
  
	<div class="text-center">
      <button type="submit" class="btn btn-primary">Save</button>
    </div>
	
  </form>
</div>
<jsp:include page="../footer.jsp"/>