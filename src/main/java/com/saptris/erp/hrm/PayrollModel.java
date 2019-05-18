package com.saptris.erp.hrm;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saptris.erp.ERPControllerServlet;
import com.saptris.erp.EntityManager;
import com.saptris.erp.TransactionalOperation;
import com.saptris.erp.hrm.db.Employee;
import com.saptris.erp.hrm.db.FixedPayheadsRates;
import com.saptris.erp.hrm.db.MonthWorkingDays;
import com.saptris.erp.hrm.db.MonthlyDetail;
import com.saptris.erp.hrm.db.Payhead;
import com.saptris.erp.hrm.db.SalaryPayment;
import com.saptris.erp.hrm.db.VariablePayheadsRates;

public class PayrollModel {
	private static final int scaleForDecimal=2;
	private static final RoundingMode roundingMode= RoundingMode.HALF_UP;
	
	public static void viewSalary(HttpServletRequest request, HttpServletResponse response) {
		// number of records to display per page
		int recordsPerPage= 10;

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

		String query= "SalaryPayment";
		String querytoNamingCaseFromCamelCase= EntityManager.toNamingCaseFromCamelCase(query);

		EntityManager entityManager= new EntityManager(query);
		int numberOfRecords= entityManager.getCount();
		int paginationSize= numberOfRecords/recordsPerPage;
		paginationSize= (numberOfRecords%recordsPerPage==0) ? paginationSize : (paginationSize+1) ;
		//skip last field of payment breakup
		String[] columns =entityManager.getAttributesName();
		columns= Arrays.copyOf(columns, columns.length-1);
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
		
		//skip last field in all rows
		for(int i=0;i<row.length;i++) {
			row[i]= Arrays.copyOf(row[i], row[i].length-1);
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
		String addAction = "generate-salary";
		request.setAttribute("addAction", addAction);
	}
	
	public static void generateSalary(HttpServletRequest request, HttpServletResponse response) {
		String []attributes, types;
		Boolean []nullable;
		//same format used in javascript
		String dateFormat="YYYY-MM-DD";
		String datetimeFormat="YYYY-MM-DD/hh:mm am/pm";
		String monthFormat="YYYY-MM";
		
		String query= request.getParameter("query");
		String status= request.getParameter("status");
		if(query==null || query.equals("null"))
			query= "SalaryPayment";
		if(status==null || status.equals("null"))
			status= "add";

		String querytoNamingCaseFromCamelCase= EntityManager.toNamingCaseFromCamelCase(query);
		
		EntityManager entityManager= new EntityManager(query);
		attributes= entityManager.getAttributesName();
		//excluse total and payment_breakup
		attributes= Arrays.copyOf(attributes, attributes.length-2);
		types= entityManager.getAttributesType();
		//excluse total(bigdecimal) and payment_breakup(map) is by defualt not included
		types= Arrays.copyOf(types, types.length-1);
		nullable= entityManager.getAttributesNullability();
		nullable= Arrays.copyOf(nullable, nullable.length-1);
		
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
		
		Map<String, Map<String,String>> dynamicDropdown= ERPControllerServlet.dynamicDropdown(entityManager, attributes, types);
		
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
		String formAction= "pay-salary";
		request.setAttribute("formAction", formAction);
	}
	
	public static String paySalary(HttpServletRequest request, HttpServletResponse response) {
		String empIdString= request.getParameter("employee");
		String monthString= request.getParameter("month");
		String idString= request.getParameter("id");
		String statusString= request.getParameter("status");
		if(empIdString==null || empIdString.equals("") || monthString==null || monthString.equals(""))
			throw new IllegalArgumentException("Wrong arguments for paySalary: "+empIdString+" & "+monthString);
		//TODO long
		int empId= Integer.parseInt(empIdString);

		try {
			EntityManager ememp= new EntityManager("Employee");
			Employee emp= (Employee)ememp.getEntity(empId);

			Map<Payhead,BigDecimal> ratesMap= new HashMap<>();
			for(Payhead pay: emp.getPayhead()) {
				ratesMap.put(pay, pay.getAmount());
			}
			//override rates for this employee
			EntityManager emfpr= new EntityManager("FixedPayheadsRates");
			List<?> list= emfpr.getEntityFor("employee", emp);
			for(Object fprob:list) {
				FixedPayheadsRates fpr= (FixedPayheadsRates)fprob;
				ratesMap.put(fpr.getPayhead(),fpr.getAmount());
			}

			Map<String, Object> attrs= new HashMap<>();
			attrs.put("employee", emp);
			Date date= Date.valueOf(monthString+"-01");
			attrs.put("month", date);

			EntityManager emvpr= new EntityManager("VariablePayheadsRates");
			list= emvpr.getEntityFor(attrs);
			for(Object fprob:list) {
				VariablePayheadsRates fpr= (VariablePayheadsRates)fprob;
				ratesMap.put(fpr.getPayhead(),fpr.getAmount());
			}

			//set basic according to attendance
			BigDecimal grossBasic= null;
			Payhead basicPayhead=null;
			for(Payhead p: ratesMap.keySet()) {
				String name= p.getPayhead_name();
				if(name.equalsIgnoreCase("basic")) {
					grossBasic= ratesMap.get(p);
					basicPayhead= p;
					break;
				}
			}
			if(grossBasic==null || basicPayhead==null) {
				throw new IllegalStateException("No Basic payhead was found");
			}
			EntityManager emmd= new EntityManager("MonthlyDetail");
			list= emmd.getEntityFor(attrs);
			int present=-1;
			for(Object fprob:list) {
				MonthlyDetail fpr= (MonthlyDetail)fprob;
				present= fpr.getPresent();
			}
			EntityManager emmwd= new EntityManager("MonthWorkingDays");
			list= emmwd.getEntityFor("month", date);
			int nowd=-1;
			for(Object fprob:list) {
				MonthWorkingDays fpr= (MonthWorkingDays)fprob;
				nowd= fpr.getNumber_of_working_days();
			}
			if(present==-1 || nowd==-1) {
				throw new IllegalStateException("No present or number of working days was set");
			}
			BigDecimal netBasic= new BigDecimal(present).multiply(grossBasic).divide(new BigDecimal(nowd),scaleForDecimal, roundingMode);
			ratesMap.put(basicPayhead, netBasic);


			Map<Payhead, BigDecimal> amtMap= new HashMap<>();
			Set<Payhead> tempset= ratesMap.keySet();
			//calculate amt of all payheads and remove from remaining list
			while(tempset.size()!=0){
				Set<Payhead> tempsetcopy= new HashSet<>();
				tempsetcopy.addAll(tempset);
				for(Payhead pay: tempsetcopy) {
					if(pay.getPayhead()==null||pay.getPayhead().size()==0) {
						amtMap.put(pay, ratesMap.get(pay));
						tempset.remove(pay);
					}
					else {
						BigDecimal bgtemp=null, bgtotal= new BigDecimal("0.0");
						boolean flag=false;
						for(Payhead temp: pay.getPayhead()) {
							if((bgtemp=amtMap.get(temp))!=null){
								bgtotal= bgtotal.add(bgtemp);
							}
							else {
								flag=true;
								break;
							}
						}
						if(!flag) {
							amtMap.put(pay, ratesMap.get(pay).multiply(bgtotal).divide(new BigDecimal("100.00"), scaleForDecimal, roundingMode));
							tempset.remove(pay);
						}
					}
				}
			}
			//System.out.println(amtMap);

			BigDecimal total= new BigDecimal("0.0");
			for(Entry<Payhead,BigDecimal> entry: amtMap.entrySet()) {
				if(entry.getKey().getType().equals("Earning"))
					total=total.add(entry.getValue());
				else if(entry.getKey().getType().equals("Deduction"))
					total=total.subtract(entry.getValue());
				else
					throw new IllegalAccessException("No such type defined:"+entry.getKey().getType());
			}

			SalaryPayment sp= new SalaryPayment();
			sp.setEmployee(emp);
			sp.setMonth(date);
			sp.setTotal(total);
			sp.setPayment_breakup(amtMap);			
			if(statusString==null || statusString.equals("") || statusString.equals("add"))
				TransactionalOperation.save(sp);
			else if(statusString.equals("update")) {
				if(idString==null || idString.equals(""))
					throw new IllegalArgumentException("No id present for update");
				//TODO long
				int id= Integer.parseInt(idString);
				sp.setSalary_id(id);
				TransactionalOperation.update(sp);
			}

			/*EntityManager emsp= new EntityManager("SalaryPayment");
			Map<String, String[]> paramMap= new HashMap<>();
			paramMap.put("employee", new String[]{empIdString});
			paramMap.put("month", new String[]{monthString});
			paramMap.put("total", new String[]{total.toString()});
			paramMap.put("payment-breakup", new String[]{...});
			emsp.saveRecord(paramMap);*/

			return ERPControllerServlet.redirectionRequest+"view-salary";
		} catch (Exception e) {
			System.out.println("Some error while testing");
			e.printStackTrace();
			return "error";
		}

	}

	public static void paySlip(HttpServletRequest request, HttpServletResponse response) {

	}
}
