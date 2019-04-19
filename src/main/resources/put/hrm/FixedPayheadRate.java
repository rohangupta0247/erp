package com.saptris.erp.db.hrm;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.saptris.erp.annotation.Attribute;

@Entity
public class FixedPayheadRate {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int payhead_id;
	@Attribute(index=0)
	private String name;
	@Attribute(index=1)
	private BigDecimal amount;
	@Attribute(index=2)
	private String calculated_on;
	@Attribute(index=3)
	private String type;
}
