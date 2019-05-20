<%@page import="java.util.Set"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="org.hibernate.HibernateException"%>
<%@page import="org.hibernate.Session"%>
<%@page import="com.saptris.erp.SessionFactoryBuilder"%>
<%@page import="com.saptris.erp.UserManager"%>
<jsp:include page="header.jsp" />
<%@page import="com.saptris.erp.EntityManager"%>
<%@page import="com.saptris.erp.JspStream"%>
<%@page isELIgnored="false"%>
<% Boolean loginStatus= (Boolean)pageContext.getAttribute("loginStatus", PageContext.REQUEST_SCOPE); 
if( loginStatus!=null && loginStatus==false){
		request.getSession(false).invalidate();
		//in this case we dont want further excecutions
		return;
}%>
<%
try{
	String user= request.getParameter("user");
	String tableName= request.getParameter("table");
	if(user==null)
		out.print("No user was mentioned");
	else if(tableName==null)
		out.print("No table name was mentioned");
	else{
	//EntityManager.dropTables(user);
	
	
		if(!UserManager.getUser().getUsername().equals(user))
			throw new IllegalStateException("Username not matched");
		Session hbnSession = null;
		try{			
			out.println(user+" loggedin<br>deleting<br>");
			hbnSession = SessionFactoryBuilder.getUserSessionFactory().openSession();
			Transaction txn = hbnSession.beginTransaction();
			//Set<String> classes= SessionFactoryBuilder.classesMapping.keySet();
			//for(String tableName: classes)
				//hbnSession.createSQLQuery("drop table "+user+"_"+EntityManager.toUnderscoreCaseFromCamelCase(tableName)).executeUpdate();
			
			String temp= tableName;
			try{
						out.println(temp+"<br>");
				if(
						hbnSession.createSQLQuery("drop table "/*+"if exists "*/+user+"_"+temp).executeUpdate()//;
						>0)
					out.println(temp+" was dropped<br>");
				else	out.println("can't drop "+temp+"<br>");
			}
			catch(Exception e){
				throw new HibernateException("Some unexpected error occured while droping "+temp, e);
			}
			
			txn.commit();
		}
		catch(Exception e){
			throw new HibernateException("Some unexpected error occured while droping all tables of this user", e);
		}
		finally{
			if(hbnSession!=null)
				hbnSession.close();
		}
	}
}
catch(Exception e){			
	JspStream.printException(e,out);
}	
%>
</body>
</html>
