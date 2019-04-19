package com.saptris.erp.db.hrm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.saptris.erp.annotation.Attribute;

@Entity
public class EmployeeApplicablePayheads {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Attribute(index=0)
	@OneToOne @JoinColumn(name="employee_id")
	private Employee employee;
	@Attribute(index=1)
	private String fixed_earning_payheads;
	@Attribute(index=2)
	private String variable_earning_payheads;
	@Attribute(index=3)
	private String fixed_deduction_payheads;
	@Attribute(index=4)
	private String variable_deduction_payheads;
}
