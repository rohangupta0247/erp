<jsp:include page="../header.jsp" />

<%@page import="com.saptris.erp.EntityManager"%>
<% Boolean loginStatus= (Boolean)pageContext.getAttribute("loginStatus", PageContext.REQUEST_SCOPE); 
if( loginStatus!=null && loginStatus==false){
		request.getSession(false).invalidate();
		//in this case we dont want further excecutions
		return;
}%>
		
<%
String query= "Employee";

EntityManager entityManager= new EntityManager(query);
int numberOfRecords= entityManager.getCount();
%>

	<div class="container">
	
	<h3 class="text-center" style="padding-bottom:20px;"> Payroll </h3>
	
	<div class="text-center" style="padding-bottom:20px;">
		<a href="payroll/save-employee?query=Employee&status=add" class="btn btn-primary" style="margin-top:20px;" role="button">ADD NEW EMPLOYEE</a>
	</div>
	
	<%if(numberOfRecords==0){ %>		
	<h5 class="text-center" style="padding-bottom:20px;">
		No Employee record found. Click on ADD NEW EMPLOYEE to add new employee to the record.
	</h5>
	
	<% } else {%>
	<br>
	Set fixed_payheads_rates<br>
Set category_rates<br>
Set pay heads(rm)->set applicable payheads list|fixed_earnings|variables|deductions
update<br>
attendance(rm)->nowd<br>
pay salary<br>
view reports<br>
		
	<%} %>
	
	</div>
	

	
<jsp:include page="../footer.jsp" />