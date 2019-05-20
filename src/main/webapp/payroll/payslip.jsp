<%@page import="java.util.Locale"%>
<%@page import="java.time.format.TextStyle"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.saptris.erp.hrm.db.Payhead"%>
<%@page import="java.util.Map"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.saptris.erp.hrm.db.SalaryPayment"%>
<%@page import="com.saptris.erp.hrm.db.Employee"%>

<%@page isELIgnored="false"%>
<% Boolean loginStatus= (Boolean)pageContext.getAttribute("loginStatus", PageContext.REQUEST_SCOPE); 
if( loginStatus!=null && loginStatus==false){
		request.getSession(false).invalidate();
		//in this case we dont want further excecutions
		return;
}%>
		
<%
boolean searchStatus= (Boolean)request.getAttribute("searchStatus");
SalaryPayment salary=null;
int nowd=-1,present=-1;
Employee employee=null;
String month=null;
LocalDate ld=null;

//TODO long
int empID= -1;
String empName= null, empDesignation= null, empDepartment= null, empDOB= null, empDOJ= null;
BigDecimal total= null;
Map<Payhead, BigDecimal> map= null;
ArrayList<String> eplist= null, dplist= null;
ArrayList<BigDecimal> ealist= null, dalist= null;
BigDecimal etotal= null, dtotal=null;
BigDecimal [] eamt=null, damt=null;
String [] epayhead=null, dpayhead=null;
String printTitle=null;

if(searchStatus==true){
salary= (SalaryPayment)request.getAttribute("salary");
nowd= (Integer)request.getAttribute("nowd");
present= (Integer)request.getAttribute("present");
	
employee= salary.getEmployee();
month= salary.getMonth().toString();

ld= LocalDate.parse(month);
month= ld.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH)+", "+ld.getYear();

empID= employee.getEmployee_id();
empName= employee.getName();
empDesignation= employee.getDesignation();
empDepartment= employee.getDepartment();
empDOB= employee.getDate_of_birth().toString();
empDOJ= employee.getDate_of_joining().toString();

total= salary.getTotal();

map= salary.getPayment_breakup();

eplist= new ArrayList<>();
dplist= new ArrayList<>();
ealist= new ArrayList<>();
dalist= new ArrayList<>();
etotal= new BigDecimal("0.0");
dtotal= new BigDecimal("0.0");

for(Entry<Payhead, BigDecimal> entry: map.entrySet()){
Payhead p= entry.getKey();
BigDecimal b= entry.getValue();
if(p.getType().equals("Earning")){
	eplist.add(p.getPayhead_name());
	ealist.add(b);
	etotal=etotal.add(b);
}
else if(p.getType().equals("Deduction")){
	dplist.add(p.getPayhead_name());
	dalist.add(b);
	dtotal=dtotal.add(b);
}
}

epayhead=eplist.toArray(new String[]{});
dpayhead=dplist.toArray(new String[]{});
eamt=ealist.toArray(new BigDecimal[]{});
damt=dalist.toArray(new BigDecimal[]{});

printTitle="Payslip-"+empName+"-"+ld.getMonth().getValue()+"-"+ld.getYear();
}
%>

<%--jsp:include page="../header.jsp" /--%>

<!DOCTYPE html>
<html lang="en">
  <head>
  <title><%=printTitle %></title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    
    <jsp:include page="/favicon.jsp" />
    
  </head>
  <body>
  
<style>
@media print {
body {-webkit-print-color-adjust: exact;}


  table { page-break-after:auto }
  tr    { page-break-inside:avoid; page-break-after:auto }
  td    { page-break-inside:avoid; page-break-after:auto }
  thead { display:table-header-group }
  tfoot { display:table-footer-group }
}

</style>

<script>
function printPage(){
var but=document.getElementById('print-button');
but.style.display="none";
window.print();
but.style.display="block";
/*
var prtContent = document.getElementById("deductions-div");
var WinPrint = window.open('', '', 'left=0,top=0,width=800,height=900,toolbar=0,scrollbars=0,status=0');
WinPrint.document.write('<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
');
WinPrint.document.write(prtContent.innerHTML);
WinPrint.document.close();
WinPrint.focus();
WinPrint.print();
WinPrint.close();
*/
}
</script>

  

  
    <div class="container">
    
    <%if(searchStatus==false){ %>
    <h3 class="text-center" style="margin-top:50px;">No such detail exists</h3>
    <%}else{ %>
    
<button id='print-button' onclick='printPage()'>Print</button>
<br>
	<h1 class="text-center"><%= empName %></h1>
	<h3 class="text-center">Pay Slip <%=month %></h3>
  <div class="text-center">
    <!--div class="col-sm-3">
      <img src="EMP_IMAGE.jpg" class="img-thumbnail" alt="IMAGE OF THE EMPLOYEE">
    </div-->
    <div>
		Employee ID:<%=empID %> <BR>
		Name: <%=empName %><BR>
		DOB: <%=empDOB %><BR>
		<%if(empDesignation!=null && !empDesignation.equals("")){ %>
		Designation: <%=empDesignation %><BR>
		<%} %>
		<%if(empDepartment!=null && !empDepartment.equals("")){ %>
		Department: <%=empDepartment %><BR>
		<%} %>
		Location: INDIA<BR>
		Date Of Joining: <%=empDOJ %><BR>
    </div>
  </div>
  <BR>
  <div class="text-center">
  <h5>ATTENDANCE DETAILS</h5>
  	  <br>
  	  TOTAL WORKING DAYS : <%=nowd %>
      <br>
      NO. OF DAYS PRESENT : <%=present %>
      
  <!--div class="col-sm-6">
      <table class="table table-hover">
		<thead>
      <tr>
        <th>ATTENDENCE DETAILS</th>
        <th>VALUE</th>
      </tr>
		<tbody>
      <tr>
        <td>TOTAL WORKING DAYS</td>
        <td>25</td>
      </tr>
	  <tr>
        <td>NO. OF DAYS PRESENT</td>
        <td>20</td>
      </tr>
	  <tr>
        <td>NO. OF DAYS ABSENT</td>
        <td>5</td>
      </tr>
	  </TBODY>
    </thead>	
	</TABLE>
  </DIV-->
  </DIV>
  <BR>
  <div>
    <div class="table-responsive">
      <table class="table table-hover">
    <thead>
      <tr>
        <th>EARNINGS</th>
        <th>AMOUNT</th>
      </tr>
    </thead>
    <tbody>
      <%for(int i=0;i<epayhead.length;i++){ %>
      <tr>
        <td><%=epayhead[i] %></td>
        <td><%=eamt[i] %></td>
      </tr>
      <%} %>
    </tbody>
  </table>
	  <table class="table table-hover">
    	<thead>
	  <tr>
        <th><U>TOTAL EARNING</U></th>
        <th><U><%=etotal %></U></th>
      </tr>
      </thead></table>
    </div>
    <div id='deductions-div' class="table-responsive">
      <table class="table table-hover">
    <thead>
      <tr>
        <th>DEDUCTIONS</th>
        <th>AMOUNT</th>
      </tr>
    </thead>
    <tbody>
      <%for(int i=0;i<dpayhead.length;i++){ %>
      <tr>
        <td><%=dpayhead[i] %></td>
        <td><%=damt[i] %></td>
      </tr>
      <%} %>
    </tbody>
  </table>
	  <table class="table table-hover">
    	<thead>
	  <tr>
        <th><U>TOTAL DEDUCTION</U></th>
        <th><U><%=dtotal %></U></th>
      </tr>
	  <tr>
        <th><B><U>NET AMOUNT</U></B></th>
        <th><B><U><%=total %></U></B></th>
      </tr>
    </thead>
      </table>
    </div>
  </div>
  
	</div>
	
	<%} %>
    <!-- jQuery first, then Tether, then Bootstrap JS. -->
    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
  </body>
</html>

<%--jsp:include page="../footer.jsp" /--%>