package com.saptris.erp.hrm.db;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.saptris.erp.EntityManager;
import com.saptris.erp.annotation.Attribute;
import com.saptris.erp.annotation.Month;
import com.saptris.erp.annotation.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class SalaryPayment {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int salary_id;
	@Attribute(index=0) @NotNull
	@OneToOne @JoinColumn(name="employee_id")
	private Employee employee;
	@Attribute(index=1) @NotNull @Month
	private Date month;
	@Attribute(index=2)
	private BigDecimal total;
	@Attribute(index=3)//FIXME map is viewin info on table row click
	@ElementCollection(fetch=FetchType.EAGER)
	private Map<Payhead, BigDecimal> payment_breakup;
	/*@OneToOne @JoinColumn(name="payhead_id")
	private Payhead payhead;
	@Attribute(index=4)
	private BigDecimal amount;
*/
	@Override
	public String toString() {
		return "SalaryPayment{"+EntityManager.separator + salary_id+ EntityManager.separator + employee + EntityManager.separator + 
				getFormattedMonthToString() + EntityManager.separator + total + EntityManager.separator + payment_breakup/*payhead + EntityManager.separator +
				amount + EntityManager.separator*/ + "}";
	}
	
	private String getFormattedMonthToString() {
		Date date= getMonth();
		if(date==null)
			return null;
		LocalDate ld= date.toLocalDate();
		int monthValue= ld.getMonthValue();
		String month=monthValue<10?"0"+monthValue:""+monthValue;
		return ld.getYear()+"-"+month;
	}
}
