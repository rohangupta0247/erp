package com.saptris.erp.hrm.db;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.saptris.erp.EntityManager;
import com.saptris.erp.annotation.Attribute;
import com.saptris.erp.annotation.Month;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class VariablePayheadsRates {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int rate_id;
	@Attribute(index=0)
	@OneToOne @JoinColumn(name="employee_id")
	private Employee employee;
	@Attribute(index=1) @Month
	private Date month;
	@Attribute(index=2)
	@OneToOne @JoinColumn(name="payhead_id")
	private Payhead payhead;
	@Attribute(index=3)
	private BigDecimal amount;

	@Override
	public String toString() {
		return "VariablePayheadsRates{"+EntityManager.separator + rate_id+ EntityManager.separator + employee + EntityManager.separator + getFormattedMonthToString() + EntityManager.separator + payhead + EntityManager.separator +
				amount + EntityManager.separator + "}";
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
