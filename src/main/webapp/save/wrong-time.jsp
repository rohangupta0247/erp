<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.saptris.erp.pmm.ReminderScheduler"%>
<%@page import="java.util.Set"%>
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


    <div class="container">
    
    <h3 class="text-center" style="padding-bottom:20px;">
    Incorrect time was entered. The entered time was before current system time. Enter time after <%=new SimpleDateFormat("yyyy-MM-dd/hh:mm a").format(ReminderScheduler.getRequiredZoneDate(new Date())) %>
    </h3>

  
<jsp:include page="../footer.jsp"/>