package com.saptris.erp.hrm.db;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.saptris.erp.EntityManager;
import com.saptris.erp.annotation.Attribute;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class FixedPayheadsRates {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int rate_id;
	@Attribute(index=0)
	@OneToOne @JoinColumn(name="employee_id")
	private Employee employee;
	@Attribute(index=1)
	@OneToOne @JoinColumn(name="payhead_id")
	private Payhead payhead;
	@Attribute(index=2)
	private BigDecimal amount;

	@Override
	public String toString() {
		return "FixedPayheadsRates{"+EntityManager.separator + rate_id+ EntityManager.separator + employee + EntityManager.separator + payhead + EntityManager.separator +
				amount + EntityManager.separator + "}";
	}
}
