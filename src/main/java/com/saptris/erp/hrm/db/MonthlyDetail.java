package com.saptris.erp.hrm.db;

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
public class MonthlyDetail {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int detail_id;
	@Attribute(index=0)
	@OneToOne @JoinColumn(name="employee_id")
	private Employee employee;
	@Attribute(index=1) @Month
	private Date month;
	@Attribute(index=2)
	private int present;
	@Attribute(index=3)
	private int overtime;
	@Attribute(index=4)
	private int production;
	@Attribute(index=5)
	private int house_rent;

	@Override
	public String toString() {
		return "MonthlyDetail{"+EntityManager.separator + detail_id+ EntityManager.separator + employee + EntityManager.separator +
				getFormattedMonthToString() + EntityManager.separator + present + EntityManager.separator + overtime + EntityManager.separator +	
				production + EntityManager.separator +  house_rent + EntityManager.separator + "}";
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
