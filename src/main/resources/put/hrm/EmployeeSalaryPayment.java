package com.saptris.erp.db.hrm;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.saptris.erp.annotation.Attribute;

@Entity
public class EmployeeSalaryPayment {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Attribute(index=0)
	@OneToOne @JoinColumn(name="employee_id")
	private Employee employee;
	@Attribute(index=1)
	private Date month;
	@Attribute(index=2)
	private BigDecimal total;
	@Attribute(index=3)
	private String payhead_amounts;
}
