package com.saptris.erp.db.hrm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.saptris.erp.annotation.Attribute;

@Entity
public class EmployeeFixedPayheadRates {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Attribute(index=0)
	@OneToOne @JoinColumn(name="employee_id")
	private Employee employee;
	@Attribute(index=1)
	private String payhead_rates;
}
