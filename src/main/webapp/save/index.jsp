<!DOCTYPE html>
<%@page import="java.util.List"%>
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

<%!String []attributes, types;
Boolean []nullable; %>
<%
String query= request.getParameter("query");
String status= request.getParameter("status");
if(query==null)
	query= "Company";
if(status==null)
	status= "add";

EntityManager entityManager= new EntityManager(query);
attributes= entityManager.getAttributesName();
types= entityManager.getAttributesType();
nullable= entityManager.getAttributesNullability();
%>

    <div class="container">
    
    <h3 class="text-center" style="padding-bottom:20px;">
    <%if(status.equals("add")) {%>
    <%="Add New" %>
    <%} else {%>
    <%="Update" %>
    <%} %>
     <%=EntityManager.toNamingCase(query) %> </h3>
     
     
     <!--script type="text/javascript">
    function validateForm()
    {
        if (document.forms["form"]["item_name"].value==null || document.forms["form"]["item_name"].value=="")
        {
            
            return false;
        }
    }
     onsubmit="return validateForm()"
	</script-->
     
  <form action="success.jsp?query=<%=query %>&status=<%=status %>" method="POST" name="form">
  
  
  <%for(int i=0; i<attributes.length; i++){ 
  	String required= "required";
	  if(nullable[i])
  		required="";
  %>
  <div class="row form-group">
      <div class="col-sm-4">
        <label><%= EntityManager.toNamingCase(attributes[i]) %></label>
      </div>
	  
      <div class="col-sm-8">
  <% 
	//Done todo task: user is null exception while refresh : by adding login modal
	if(types[i].equals("text")){%>
		<input <%= required %> type="text" placeholder="enter <%= EntityManager.toRunningSpaceCase(attributes[i]) %> here" class="form-control" name="<%= EntityManager.toRunningCase(attributes[i]) %>">
	<% }
	else if(types[i].equals("number")){%>
	<input <%= required %> type="number" placeholder="enter <%= EntityManager.toRunningSpaceCase(attributes[i]) %> here" class="form-control" name="<%= EntityManager.toRunningCase(attributes[i]) %>">
<% }
	else if(types[i].equals("numberAnyStep")){%>
	<input <%= required %> type="number" step="any" placeholder="enter <%= EntityManager.toRunningSpaceCase(attributes[i]) %> here" class="form-control" name="<%= EntityManager.toRunningCase(attributes[i]) %>">
<% }
	else if(types[i].equals("date")){%>
	<input <%= required %> type="date" placeholder="enter <%= EntityManager.toRunningSpaceCase(attributes[i]) %> here" class="form-control" name="<%= EntityManager.toRunningCase(attributes[i]) %>">
<% }
	else if(types[i].equals("select")){
		//in case of select, attribute name will be variable name of attribute class in the entity class
		EntityManager foreignEntity= new EntityManager(EntityManager.toNamingCase(attributes[i])/*.split(" ")[0]*/);
		List<?> foreignValues= foreignEntity.getAllEntity();
		%>
		<select <%= required %> class="form-control" name="<%= EntityManager.toRunningCase(attributes[i]) %>">
		<option value="">--select--</option>
		<%
		for(int j=0;j<foreignValues.size();j++){
			//store id of entity in "value" and complete detail in inner text
			String optionValue= foreignValues.get(j).toString().split(EntityManager.separator)[1];
			String optionShow= foreignValues.get(j).toString().split(EntityManager.separator)[1]+
					 " " +foreignValues.get(j).toString().split(EntityManager.separator)[2];
			%>
			<option value="<%= EntityManager.toRunningCase(optionValue) %>"><%= optionShow %></option>
		<% } %>
		</select>
	<% } %>
	</div>
    </div>
<%} %>
  
	<div class="text-center">
      <button type="submit" class="btn btn-primary">Save</button>
    </div>
	
  </form>
</div>
<jsp:include page="../footer.jsp"/>