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
		<a href="save-employee?query=Employee&status=add" class="btn btn-primary" style="margin-top:20px;" role="button">ADD NEW EMPLOYEE</a>
	</div>
	
	<%if(numberOfRecords==0){ %>		
	<h5 class="text-center" style="padding-bottom:20px;">
		No Employee record found. Click on ADD NEW EMPLOYEE to add new employee to the record.
	</h5>
	
	<% } else {%>
	<div class="text-center" style="padding-bottom:20px;">
	
	<a href="view?query=Payhead" class="btn btn-primary" style="margin-top:20px;" role="button">Manage Payheads</a><br>
	<a href="view?query=FixedPayheadsRates" class="btn btn-primary" style="margin-top:20px;" role="button">Fixed Payheads Rates for Employee</a><br>
	<a href="view?query=VariablePayheadsRates" class="btn btn-primary" style="margin-top:20px;" role="button">Monthly Payheads Rates for Employee</a><br>
	<a href="view?query=MonthlyDetail" class="btn btn-primary" style="margin-top:20px;" role="button">Monthly Employee Work and Details</a><br>
	<a href="view?query=MonthWorkingDays" class="btn btn-primary" style="margin-top:20px;" role="button">Number of working days</a><br>
	<a href="view-salary" class="btn btn-primary" style="margin-top:20px;" role="button">View Salary</a><br>
	<a href="payslip" class="btn btn-primary" style="margin-top:20px;" role="button">View Pay Slip</a><br>
	<!--a href="#" class="btn btn-primary" style="margin-top:20px;" role="button">*****Set Category Rates</a><br-->
	
	</div>
	<%} %>
	
	</div>
	
<hr>
<h6 class="text-center" style="padding-bottom:20px;">
		Employee count : <%= numberOfRecords %>
		<br>
		<a href="view?query=Employee" class="btn btn-primary" style="margin-top:20px;" role="button">View Employees</a>
	</h6>
	
<jsp:include page="../footer.jsp" />