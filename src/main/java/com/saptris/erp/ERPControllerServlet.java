package com.saptris.erp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saptris.erp.annotation.Attribute;
import com.saptris.erp.hrm.PayrollModel;

@WebServlet(name = "ERPControllerServlet", urlPatterns = "/")
public class ERPControllerServlet extends HttpServlet {
	//-> in localhost-> /ERP/... so substring used
	//private int rootURLLength= "/ERP/".length();
	//after deploying -> site has "/" root
	private int rootURLLength= "/".length();
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3059936347418983129L;

	private static final String noDispatch= "<no-dispatch>";
	public static final String redirectionRequest="<redirect>:";
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		String requestURI= request.getRequestURI();
		
		String dispatchURI;
		//dispatchURI=requestURI;
		dispatchURI=noDispatch;
		requestURI= requestURI.substring(rootURLLength);
		String tempRequest="";
		//deployments have a favicon.ico call for every request
		if(UserManager.isLoggedout() && !requestURI.equals("home") && !requestURI.equals("validateUser") && !requestURI.equals("signup") && !requestURI.equals("image") && !requestURI.equals("favicon.ico")) {
			tempRequest= requestURI;
			requestURI= "login";
		}
		
		switch (requestURI) {
		case "favicon.ico":
			downloadFavicon(response);
			break;
		case "home":
			dispatchURI= "/index.jsp";
			break;
		case "login":
			String queryString= request.getQueryString();
			if(queryString!=null) {
				queryString="?"+queryString.replaceAll("&", "%26");
			}
			else
				queryString="";
			
			//this is required for home page which directly calls login page
			if(!tempRequest.equals("login")) {
				tempRequest= request.getRequestURI();
				if(tempRequest.endsWith("login")) {
					// "/ERP/login"
					//when login page is opened even when user logged in
					tempRequest= "home";
				}
			}
			else {
				queryString="";
				tempRequest="home";
			}
			
			dispatchURI= "/login/index.jsp?from="+tempRequest+queryString;
			break;
		case "validateUser":
			dispatchURI= validateUser(request, response);
			break;
		case "signup":
			dispatchURI= "/signup/index.jsp";
			break;
		case "logout":
			dispatchURI= "/logout/index.jsp";
			break;
		case "save":
			dispatchURI= "/save/index.jsp";
			save(request, response);
			break;
		case "saveRecord":
			dispatchURI= saveRecord(request, response);
			break;
		case "view":
			dispatchURI= "/view/index.jsp";
			view(request, response);
			break;
		case "getEntityInJSArray":
			//dispatchURI= noDispatch;
			getEntityInJSArray(request, response);
			break;
		case "deleteEntity":
			//dispatchURI= noDispatch;
			deleteEntity(request, response);
			break;
		case "error":
			dispatchURI= "error500.jsp";
			break;
		case "image":
			//dispatchURI= noDispatch;
			downloadImage(request, response);
			break;
		case "payroll":
			dispatchURI= "/payroll/index.jsp";
			break;
		case "save-employee":
			dispatchURI= "/save/index.jsp";
			save(request, response);
			break;
		case "view-salary":
			dispatchURI= "/view/index.jsp";
			PayrollModel.viewSalary(request, response);
			break;
		case "generate-salary":
			dispatchURI= "/save/index.jsp";
			PayrollModel.generateSalary(request, response);
			break;
		case "pay-salary":
			dispatchURI= PayrollModel.paySalary(request, response);
			break;
		case "select-employee":
			dispatchURI= "/save/index.jsp";
			PayrollModel.selectEmployee(request, response);
			break;
		case "payslip":
			dispatchURI= "/payroll/payslip.jsp";
			PayrollModel.paySlip(request, response);
			break;
		default:
			System.out.println("No mapping done for "+requestURI);
			dispatchURI= "error404.jsp";
		}
		if(dispatchURI.equals(noDispatch)) {
			//response.sendRedirect(requestURI);
			return;
		}
		else if(dispatchURI.startsWith(redirectionRequest)) {
			dispatchURI= dispatchURI.substring(redirectionRequest.length());
			//response.sendRedirect(redirectURI);
			response.getWriter().print("<script>\r\n" + 
					"	window.location.replace('"+dispatchURI+"');\r\n" + 
					"</script>");
			return;
		}
		dispatcher= request.getRequestDispatcher(dispatchURI);
		try {
			dispatcher.forward(request, response);
		}
		catch(IllegalStateException ex) {
			if(ex.getMessage().equals("Cannot forward after response has been committed")) {
				System.out.println("response was committed");
			}
			else {
				throw ex;
			}
		}
	}
	
	private String validateUser(HttpServletRequest request, HttpServletResponse response) {
		//no need now on context loading path is set
		//SessionFactoryBuilder.setRealPath(getServletContext().getRealPath(""));
		
		HttpSession session= request.getSession();
		String username= request.getParameter("username");
		String password= request.getParameter("password");
		try{
		User u=new User();
		int status= UserManager.validateLogin(username, password,u,session.hashCode());

		switch (status) {
		case UserManager.VALID_USER:
			//session= request.getSession();
			session.setAttribute("username", u.getUsername());
			session.setAttribute("name", u.getName());
			session.setAttribute("email", u.getEmail());
			session.setAttribute("pass", u.getPass());
			
			//out.print("Welcome "+session.getAttribute("name").toString());
			
			String from= request.getParameter("from");
			if(from==null)
				//from="../";
				from="home";
			//response.sendRedirect(from);
			return redirectionRequest+from;
		//break;
		case UserManager.INVALID_USERNAME:
			session.invalidate();
			//out.print("Not signed up yet, go to signup<br><a href=../signup>SignUp</a>");
			//response.sendRedirect("../signup");
			return redirectionRequest+"signup";
		//break;
		case UserManager.INVALID_PASSWORD:
			session.invalidate();
			//out.print("Wrong password");
			request.setAttribute("invalidUser",true);
			return "login";
			//break;
		default:
			throw new IllegalStateException("Invalid status value from UserManager.validateLogin()");
			//break;
		}
		}
	    catch(Exception e){
	    	session.invalidate();
	    	//JspStream.printException(e,out);
	    	e.printStackTrace();
	    	return "error";
	    }
	}
	
	private void view(HttpServletRequest request, HttpServletResponse response) {
		// number of records to display per page
		int recordsPerPage= 10;

		//default page
		String defaultPage="Company";
			
		/*if(recordsPerPage<1){
			out.print("Error : Showing 0 records on every page");
			return;
		}*/
		
		int pageNumber= 0;
		String pageString= request.getParameter("page");
		if(pageString==null)
			pageNumber=1;
		else
			pageNumber= Integer.parseInt(pageString);

		String query= request.getParameter("query");
		if(query==null){
			/*response.sendRedirect("..");
			return;*/
			
			//default page is of company now
			query=defaultPage;
		}
		String querytoNamingCaseFromCamelCase= EntityManager.toNamingCaseFromCamelCase(query);
		
		EntityManager entityManager= new EntityManager(query);
		int numberOfRecords= entityManager.getCount();
		int paginationSize= numberOfRecords/recordsPerPage;
		paginationSize= (numberOfRecords%recordsPerPage==0) ? paginationSize : (paginationSize+1) ;
		String[] columns =entityManager.getAttributesName();
		String[] columnstoNamingCase = new String[columns.length];
		String[][] row;

		String search= request.getParameter("search"); 
		String searchItem= request.getParameter("searchquery");
		
		
		int count= recordsPerPage;
		int start= (pageNumber-1)*recordsPerPage+1;
		int diff= numberOfRecords-start;
		if(diff<recordsPerPage)
			count= diff+1;

		if(search==null || search.equals("")){
			row= entityManager.getAllEntityString(count, start);
		}
		else{
			if(searchItem==null || searchItem.equals(""))
				row= entityManager.getAllEntityString(count, start);
			else {
				paginationSize=0;
				row= entityManager.search(search, searchItem);
			}
		}
		
		int x=0;
		for(String col: columns) {
			columnstoNamingCase[x++]= EntityManager.toNamingCase(col);
		}
		

		for(int i=0; i<row.length; i++){ 
			for(int j=0; j<row[i].length; j++){ 
				if(row[i][j].startsWith(EntityManager.idSeparator)) {
					//seprator#ClassName#id
					row[i][j]= row[i][j].split("#")[2];
				}
			}
		}
		
		/*
		 String query= request.getParameter("query");
		String querytoNamingCaseFromCamelCase= EntityManager.toNamingCaseFromCamelCase((String)request.getAttribute("query"));
		int numberOfRecords= (Integer)request.getAttribute("numberOfRecords");
		int pageNumber= (Integer)request.getAttribute("pageNumber");
		int recordsPerPage= (Integer)request.getAttribute("recordsPerPage");
		int paginationSize= (Integer)request.getAttribute("paginationSize");
		String search= (String)request.getAttribute("search");
		String searchItem= (String)request.getAttribute("searchItem");
		String columns[] = (String[])request.getAttribute("columns");
		String columnstoNamingCase[] = (String[])request.getAttribute("columnstoNamingCase");
		String row[][] = (String[][])request.getAttribute("row");
		String addAction = (String)request.getAttribute("addAction");
		 */
		
		request.setAttribute("query", query);
		request.setAttribute("querytoNamingCaseFromCamelCase", querytoNamingCaseFromCamelCase);
		request.setAttribute("numberOfRecords", numberOfRecords);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("recordsPerPage", recordsPerPage);
		request.setAttribute("paginationSize", paginationSize);
		request.setAttribute("search", search);
		request.setAttribute("searchItem", searchItem);
		request.setAttribute("columns", columns);
		request.setAttribute("columnstoNamingCase", columnstoNamingCase);
		request.setAttribute("row", row);
		request.setAttribute("idSeparator", EntityManager.idSeparator);
		String addAction = "save";
		request.setAttribute("addAction", addAction);
	}
	
	private void save(HttpServletRequest request, HttpServletResponse response) {
		String []attributes, types;
		Boolean []nullable;
		//same format used in javascript
		String dateFormat="YYYY-MM-DD";
		String datetimeFormat="YYYY-MM-DD/hh:mm am/pm";
		String monthFormat="YYYY-MM";
		
		String query= request.getParameter("query");
		String status= request.getParameter("status");
		if(query==null || query.equals("null"))
			query= "Company";
		if(status==null || status.equals("null"))
			status= "add";

		String querytoNamingCaseFromCamelCase= EntityManager.toNamingCaseFromCamelCase(query);
		
		EntityManager entityManager= new EntityManager(query);
		attributes= entityManager.getAttributesName();
		types= entityManager.getAttributesType();
		nullable= entityManager.getAttributesNullability();
		
		String [] attributestoNamingCase= new String[attributes.length];
		int i=0;
		for(String stri: attributes) {
			attributestoNamingCase[i++]= EntityManager.toNamingCase(stri);
		}
		String [] attributestoRunningCase= new String[attributes.length];
		i=0;
		for(String stri: attributes) {
			attributestoRunningCase[i++]= EntityManager.toRunningCase(stri);
		}
		String [] attributestoRunningSpaceCase= new String[attributes.length];
		i=0;
		for(String stri: attributes) {
			attributestoRunningSpaceCase[i++]= EntityManager.toRunningSpaceCase(stri);
		}
		
		Map<String, Map<String,String>> dynamicDropdown= dynamicDropdown(entityManager, attributes, types);
		
		/*
		String query= (String)request.getAttribute("query");
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
		String formAction = (String)request.getAttribute("formAction");
		*/
		request.setAttribute("query", query);
		request.setAttribute("querytoNamingCaseFromCamelCase", querytoNamingCaseFromCamelCase);
		request.setAttribute("status", status);
		request.setAttribute("dateFormat", dateFormat);
		request.setAttribute("datetimeFormat", datetimeFormat);
		request.setAttribute("monthFormat", monthFormat);
		request.setAttribute("attributes", attributes);
		request.setAttribute("attributestoNamingCase", attributestoNamingCase);
		request.setAttribute("attributestoRunningCase", attributestoRunningCase);
		request.setAttribute("attributestoRunningSpaceCase", attributestoRunningSpaceCase);
		request.setAttribute("types", types);
		request.setAttribute("nullable", nullable);
		request.setAttribute("idSeparator", EntityManager.idSeparator);
		request.setAttribute("dynamicDropdown", dynamicDropdown);
		if(status.equals("update")) {
			String id= request.getParameter("id");
			request.setAttribute("id", id);
		}
		String formAction= "saveRecord";
		request.setAttribute("formAction", formAction);
	}
	
	public static Map<String, Map<String,String>> dynamicDropdown(EntityManager entityManager, String []attributes, String []types){
		Map<String, Map<String,String>> result= new HashMap<>();
		String selectKey, optionValuetoRunningCase, optionShow;
		int i=0;
		//in case of select, attribute name will be variable name of attribute class in the entity class
		for(i=0;i<types.length;i++) {
			Map<String,String> temp= new TreeMap<>();
			selectKey= attributes[i];
			
			if(types[i].equals("select") || types[i].equals("add-more")) {
				EntityManager foreignEntity= new EntityManager(EntityManager.toCamelCase(attributes[i])/*.split(" ")[0]*/);
				List<?> foreignValues= foreignEntity.getAllEntity();
				for(int j=0;j<foreignValues.size();j++){
					//store id of entity in "value" and complete detail in inner text
					optionValuetoRunningCase= EntityManager.toRunningCase(foreignValues.get(j).toString().split(EntityManager.separator)[1]);
					optionShow= foreignValues.get(j).toString().split(EntityManager.separator)[1]+
							 " " +foreignValues.get(j).toString().split(EntityManager.separator)[2];
					temp.put(optionValuetoRunningCase, optionShow);
				}
			}
			else if(types[i].equals("options")) {
				try {
					Field field = entityManager.getClassType().getDeclaredField(selectKey);
					Attribute attr= field.getAnnotation(Attribute.class);
					String[] foreignValues= attr.values();
					
					
					for(int j=0;j<foreignValues.length;j++){
						//store string in "value" and in inner text
						optionShow= optionValuetoRunningCase= foreignValues[j];
						temp.put(optionValuetoRunningCase, optionShow);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Some unexpected error occured while setting options");
				}
			}
			result.put(selectKey, temp);
		}
		return result;
	}
	
	
	private String saveRecord(HttpServletRequest request, HttpServletResponse response) {
		EntityManager entityManager= new EntityManager(request.getParameter("query"));
		try{
			/*if(request.getParameter("status").equals("add"))
				entityManager.saveRecord(request.getParameterMap(), EntityManager.ADD_RECORD);
			else if(request.getParameter("status").equals("update"))
				entityManager.saveRecord(request.getParameterMap(), EntityManager.UPDATE_RECORD);*/
			entityManager.saveRecord(request.getParameterMap());

			if(request.getParameter("query").equals("MaintenanceAllUsers"))
				//response.sendRedirect("../");
				return redirectionRequest+"home";
			else
				//response.sendRedirect("../view?query="+request.getParameter("query"));
				return redirectionRequest+"view?query="+request.getParameter("query");
		}
		catch(Exception e){
			//JspStream.printException(e,response.getWriter());
			e.printStackTrace();
			return "error";
		}
		//		<script>
		//		window.location.replace("home");
		//		</script>
	}
	
	private void getEntityInJSArray(HttpServletRequest request, HttpServletResponse response) {
		String query= request.getParameter("query");
		if(query==null) {
			throw new IllegalStateException("No query found in query string");
		}
		String idString= request.getParameter("id");
		if(idString==null) {
			throw new IllegalStateException("No id found in query string");
		}
		
		try {
			PrintWriter out= response.getWriter();
			//response.setContentType("text/html");
			response.setContentType("application/json");
			out.print(entityInJSArray(query, idString));
		} catch (IOException e) {
			System.out.println("Some error while getting entity");
			e.printStackTrace();
		}
		
	}
	
	private String entityInJSArray(String query, String idString) {
		//TODO id in long
		int id= Integer.parseInt(idString); 
		EntityManager em= new EntityManager(query);
		
		String res[]= EntityManager.mapObject(em.getEntity(id));
		String cols[]= em.getAttributesName();
		String colsNaming[]= new String[cols.length];
		int i=0;
		for(String temp: cols)
			colsNaming[i++]=EntityManager.toNamingCase(temp);
		String s="{\"entity\":[\""+res[0]+"\"";
		for(i=1;i<res.length;i++) {
			if(!res[i].startsWith("["))
				s+=", \""+res[i]+"\"";
			else
				s+=", "+res[i];
		}
		s+="], \"attrs\":[\"ID\"";
		for(i=0;i<colsNaming.length;i++) {
			s+=", \""+colsNaming[i]+"\"";
		}
		s+="]}";
		return s;
	}
	
	private void deleteEntity(HttpServletRequest request, HttpServletResponse response) {
		String query= request.getParameter("query");
		if(query==null) {
			throw new IllegalStateException("No query found in query string");
		}
		String idString= request.getParameter("id");
		if(idString==null) {
			throw new IllegalStateException("No id found in query string");
		}
		
		try {
			PrintWriter out= response.getWriter();
			//response.setContentType("text/html");
			response.setContentType("application/json");
			
			//TODO long
			int id= Integer.parseInt(idString);
			out.print("{\"deleted\":"+new EntityManager(query).delete(id)+"}"); 
		} catch (IOException e) {
			System.out.println("Some error while deleting entity");
			e.printStackTrace();
		}
		
	}
	
	private void downloadImage(HttpServletRequest request, HttpServletResponse response) {
		String imageName= request.getParameter("image");
		if(imageName==null) {
			throw new IllegalStateException("No image found in query string");
		}
		String imagePath= getServletContext().getRealPath("WEB-INF/classes/images/"+imageName);
		
		ServletOutputStream stream= null;
		BufferedInputStream buf= null;
		FileInputStream input= null;
		try {
			stream= response.getOutputStream();
			File doc= new File(imagePath);
			response.setContentType("image/jpeg");
			response.addHeader("Content-Disposition", "attachment;filename="+imageName);
			response.setContentLengthLong(doc.length());
			
			input= new FileInputStream(doc);
			buf= new BufferedInputStream(input);
			int readBytes=0;
			while((readBytes=buf.read())!=-1) {
				stream.write(readBytes);
			}
		} catch (Exception e) {
			System.out.println("Error in downloading image");
			e.printStackTrace();
		}
		finally {
			try {
				if(stream!=null) 
					stream.close();
				if(buf!=null) 
					buf.close();
				if(input!=null) 
					input.close();
			} catch (IOException e) {
				System.out.println("Error in closing some resources while downloading");
				e.printStackTrace();
			}
		}
	}
	
	private void downloadFavicon(HttpServletResponse response) {
		String imageName= "favicon.ico";
		String imagePath= getServletContext().getRealPath("/"+imageName);
		
		ServletOutputStream stream= null;
		BufferedInputStream buf= null;
		FileInputStream input= null;
		try {
			stream= response.getOutputStream();
			File doc= new File(imagePath);
			response.setContentType("image/jpeg");
			response.addHeader("Content-Disposition", "attachment;filename="+imageName);
			response.setContentLengthLong(doc.length());
			
			input= new FileInputStream(doc);
			buf= new BufferedInputStream(input);
			int readBytes=0;
			while((readBytes=buf.read())!=-1) {
				stream.write(readBytes);
			}
		} catch (Exception e) {
			System.out.println("Error in downloading image");
			e.printStackTrace();
		}
		finally {
			try {
				if(stream!=null) 
					stream.close();
				if(buf!=null) 
					buf.close();
				if(input!=null) 
					input.close();
			} catch (IOException e) {
				System.out.println("Error in closing some resources while downloading");
				e.printStackTrace();
			}
		}
	}
}
