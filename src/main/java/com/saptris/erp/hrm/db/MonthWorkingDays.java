package com.saptris.erp.hrm.db;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.saptris.erp.EntityManager;
import com.saptris.erp.annotation.Attribute;
import com.saptris.erp.annotation.Month;
import com.saptris.erp.annotation.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class MonthWorkingDays {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int month_id;
	@Attribute(index=0) @NotNull @Month
	private Date month;
	@Attribute(index=1) @NotNull
	private int number_of_working_days;

	@Override
	public String toString() {
		return "MonthlyDetail{"+EntityManager.separator + month_id + EntityManager.separator + getFormattedMonthToString() + 
				EntityManager.separator + number_of_working_days + EntityManager.separator + "}";
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
