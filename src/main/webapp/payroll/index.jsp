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
	
	<!--a href="view?query=Payhead" class="btn btn-primary" style="margin-top:20px;" role="button">Manage Payheads</a>
	<a href="view?query=FixedPayheadsRates" class="btn btn-primary" style="margin-top:20px;" role="button">Fixed Payheads Rates for Employee</a>
	<a href="view?query=VariablePayheadsRates" class="btn btn-primary" style="margin-top:20px;" role="button">Monthly Payheads Rates for Employee</a><br>
	<a href="view?query=MonthlyDetail" class="btn btn-primary" style="margin-top:20px;" role="button">Monthly Employee Work and Details</a>
	<a href="view?query=MonthWorkingDays" class="btn btn-primary" style="margin-top:20px;" role="button">Number of working days</a><br>
	<a href="view-salary" class="btn btn-primary" style="margin-top:20px;" role="button">View Salary</a>
	<a href="select-employee" class="btn btn-primary" style="margin-top:20px;" role="button">View Pay Slip</a><br-->
	<!--a href="#" class="btn btn-primary" style="margin-top:20px;" role="button">*****Set Category Rates</a><br-->
	
	<div class="card-deck" style="margin-bottom:20px;">
    
    <div class="col" align="center">
    <div class="row">
    <div class="col" align="center" style="margin-top:10px;margin-bottom:10px;">
    <div class="card bg-primary">
      <div class="card-body text-center">
        <p class="card-text">Manage all payheads applicable in your enterprise</p>
      </div>
      </div>
    </div>
    </div>
    <div class="row">
    <div class="col" align="center">
	<a href="view?query=Payhead" class="btn btn-outline-primary" role="button">Manage Payheads</a>
    </div>
    </div>
	</div>
	
    <div class="col" align="center">
    <div class="row">
    <div class="col" align="center" style="margin-top:10px;margin-bottom:10px;">
    <div class="card bg-success">
      <div class="card-body text-center">
        <p class="card-text">Manage all fixed rate payheads which remains same for an employee</p>
      </div>
      </div>
    </div>
    </div>
    <div class="row">
    <div class="col" align="center">
	<a href="view?query=FixedPayheadsRates" class="btn btn-outline-primary" role="button">Fixed Payheads Rates for Employee</a>
    </div>
    </div>
	</div>
	
	<div class="col" align="center">
    <div class="row">
    <div class="col" align="center" style="margin-top:10px;margin-bottom:10px;">
    <div class="card bg-warning">
      <div class="card-body text-center">
        <p class="card-text">Manage all monthly based rate payheads which changes every month for all employees</p>
      </div>
      </div>
    </div>
    </div>
    <div class="row">
    <div class="col" align="center">
	<a href="view?query=VariablePayheadsRates" class="btn btn-outline-primary" role="button">Monthly Payheads Rates for Employee</a>
    </div>
    </div>
	</div>
	
  </div>
  
  <div class="card-deck" style="margin-top:20px;">
    
    <div class="col" align="center">
    <div class="row">
    <div class="col" align="center" style="margin-top:10px;margin-bottom:10px;">
    <div class="card bg-primary">
      <div class="card-body text-center">
        <p class="card-text">Monthly details of employee like attendance or over time or amount of work done and house rent paid</p>
      </div>
      </div>
    </div>
    </div>
    <div class="row">
    <div class="col" align="center">
	<a href="view?query=MonthlyDetail" class="btn btn-outline-primary" role="button">Monthly Employee Work and Details</a>
    </div>
    </div>
	</div>
	
    <div class="col" align="center">
    <div class="row">
    <div class="col" align="center" style="margin-top:10px;margin-bottom:10px;">
    <div class="card bg-warning">
      <div class="card-body text-center">
        <p class="card-text">Set number of working days for a month</p>
      </div>
      </div>
    </div>
    </div>
    <div class="row">
    <div class="col" align="center">
	<a href="view?query=MonthWorkingDays" class="btn btn-outline-primary" role="button">Number of working days</a>
    </div>
    </div>
	</div>
	
    </div>
	
  <div class="card-deck" style="margin-top:20px;margin-bottom:20px;">
      
    <div class="col" align="center">
    <div class="row">
    <div class="col" align="center" style="margin-top:10px;margin-bottom:10px;">
    <div class="card bg-primary">
      <div class="card-body text-center">
        <p class="card-text">Pay salary and view all totals for all employees each month</p>
      </div>
      </div>
    </div>
    </div>
    <div class="row">
    <div class="col" align="center">
		<a href="view-salary" class="btn btn-outline-primary" role="button">View Salary</a>
    </div>
    </div>
	</div>
	
    <div class="col" align="center">
    <div class="row">
    <div class="col" align="center" style="margin-top:10px;margin-bottom:10px;">
    <div class="card bg-warning">
      <div class="card-body text-center">
        <p class="card-text">Generate payslip of salary</p>
      </div>
      </div>
    </div>
    </div>
    <div class="row">
    <div class="col" align="center">
	<a href="select-employee" class="btn btn-outline-primary" role="button">View Pay Slip</a>
    </div>
    </div>
	</div>
	
  </div>
	
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