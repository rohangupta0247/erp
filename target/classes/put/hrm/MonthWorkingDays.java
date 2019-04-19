package com.saptris.erp.db.hrm;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.saptris.erp.annotation.Attribute;

@Entity
public class MonthWorkingDays {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Attribute(index=0)
	private Date month;
	@Attribute(index=1)
	private int working_days;
}
