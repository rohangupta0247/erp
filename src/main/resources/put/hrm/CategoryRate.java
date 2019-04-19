package com.saptris.erp.db.hrm;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.saptris.erp.annotation.Attribute;

@Entity
public class CategoryRate {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int category_id;
	@Attribute(index=0)
	private String department_name;
	@Attribute(index=1)
	private String designation_name;
	@Attribute(index=2)
	private BigDecimal overtime_rate;
	@Attribute(index=3)
	private BigDecimal production_rate;
	@Attribute(index=4)
	private BigDecimal basic;
	@Attribute(index=5)
	private BigDecimal hra;
	@Attribute(index=6)
	private BigDecimal conveyance;
}
