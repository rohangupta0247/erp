<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="com.saptris.erp.UserManager"%>
<jsp:include page="../header.jsp" />
<%@page import="java.util.List"%>
<%@page errorPage="error500.jsp"%>
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
}
</script>
     
 <%/* %> <form action="success.jsp?query=<%=query %>&status=<%=status %>" method="POST" name="form" onsubmit="return validateForm()"> <%*/ %>
  <form action="saveRecord?query=<%=query %>&status=<%=status %>" method="POST" name="form" onsubmit="return validateForm()">
  
  
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
	//Done todo task: user is null exception while refresh : by adding login modal
	
	//TODO input types in this method, getAttributesType and saveRecord method of EntityManager
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
	else if(types[i].equals("select")){
		
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