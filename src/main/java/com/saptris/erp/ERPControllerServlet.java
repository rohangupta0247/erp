package com.saptris.erp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

@WebServlet(name = "ERPControllerServlet", urlPatterns = "/")
public class ERPControllerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3059936347418983129L;

	private static final String noDispatch= "<no-dispatch>";
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		String requestURI= request.getRequestURI();
		System.out.println("ERPControllerServlet was called by "+requestURI);
		String dispatchURI;
		//dispatchURI=requestURI;
		dispatchURI=noDispatch;
		//-> /ERP/... so substring used
		requestURI= requestURI.substring(5);
		if(UserManager.isLoggedout())
			requestURI="login";
		switch (requestURI) {
		case "login":
			dispatchURI= "/login/index.jsp";
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
		case "error":
			dispatchURI= "error500.jsp";
			break;
		case "error500-image":
			//dispatchURI= noDispatch;
			downloadError500Image(request, response);
			break;
		case "payroll":
			dispatchURI= "/payroll/index.jsp";
			break;
		case "payroll/save-employee":
			dispatchURI= "/payroll/save.jsp";
			break;
			
		default:
			System.out.println("No mapping done for "+requestURI);
		}
		if(dispatchURI.equals(noDispatch))
			return;
		dispatcher= request.getRequestDispatcher(dispatchURI);
		dispatcher.forward(request, response);
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
	}
	
	private void save(HttpServletRequest request, HttpServletResponse response) {
		String []attributes, types;
		Boolean []nullable;
		//same format used in javascript
		String dateFormat="YYYY-MM-DD";
		String datetimeFormat="YYYY-MM-DD/hh:mm am/pm";
		
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
		
		Map<String, Map<String,String>> dynamicDropdown= dynamicDropdown(attributes, types);
		
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
		*/
		request.setAttribute("query", query);
		request.setAttribute("querytoNamingCaseFromCamelCase", querytoNamingCaseFromCamelCase);
		request.setAttribute("status", status);
		request.setAttribute("dateFormat", dateFormat);
		request.setAttribute("datetimeFormat", datetimeFormat);
		request.setAttribute("attributes", attributes);
		request.setAttribute("attributestoNamingCase", attributestoNamingCase);
		request.setAttribute("attributestoRunningCase", attributestoRunningCase);
		request.setAttribute("attributestoRunningSpaceCase", attributestoRunningSpaceCase);
		request.setAttribute("types", types);
		request.setAttribute("nullable", nullable);
		request.setAttribute("dynamicDropdown", dynamicDropdown);
	}
	
	private Map<String, Map<String,String>> dynamicDropdown(String []attributes, String []types){
		Map<String, Map<String,String>> result= new HashMap<>();
		Map<String,String> temp= new TreeMap<>();
		String selectKey, optionValuetoRunningCase, optionShow;
		int i=0;
		//in case of select, attribute name will be variable name of attribute class in the entity class
		for(i=0;i<types.length;i++) {
			if(types[i].equals("select")) {
				selectKey= attributes[i];
				EntityManager foreignEntity= new EntityManager(EntityManager.toNamingCase(attributes[i])/*.split(" ")[0]*/);
				List<?> foreignValues= foreignEntity.getAllEntity();
				for(int j=0;j<foreignValues.size();j++){
					//store id of entity in "value" and complete detail in inner text
					optionValuetoRunningCase= EntityManager.toRunningCase(foreignValues.get(j).toString().split(EntityManager.separator)[1]);
					optionShow= foreignValues.get(j).toString().split(EntityManager.separator)[1]+
							 " " +foreignValues.get(j).toString().split(EntityManager.separator)[2];
					temp.put(optionValuetoRunningCase, optionShow);
				}
				result.put(selectKey, temp);
			}
		}
		return result;
	}
	
	private String saveRecord(HttpServletRequest request, HttpServletResponse response) {
		EntityManager entityManager= new EntityManager(request.getParameter("query"));
		try{
			if(request.getParameter("status").equals("add"))
				entityManager.saveRecord(request.getParameterMap(), EntityManager.ADD_RECORD);
			else if(request.getParameter("status").equals("update"))
				entityManager.saveRecord(request.getParameterMap(), EntityManager.UPDATE_RECORD);

			if(request.getParameter("query").equals("MaintenanceAllUsers"))
				//response.sendRedirect("../");
				return "home";
			else
				//response.sendRedirect("../view?query="+request.getParameter("query"));
				return "view?query="+request.getParameter("query");
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
	
	private void downloadError500Image(HttpServletRequest request, HttpServletResponse response) {
		String imageName= "error500.jpg";
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
			System.out.println("Error in downloading error500 image");
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
