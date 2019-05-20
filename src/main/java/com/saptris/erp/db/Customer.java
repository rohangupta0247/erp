package com.saptris.erp.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.saptris.erp.EntityManager;
import com.saptris.erp.annotation.Attribute;
import com.saptris.erp.annotation.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Customer {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int customer_id;
	@Attribute(index=0) @NotNull
	private String customer_name;
	@Attribute(index=1) @NotNull
	private String address;
	@Attribute(index=2) @NotNull
	private String state;
	@Attribute(index=3)
	private int phone_number;
	@Attribute(index=4)
	private String email;
	@Attribute(index=5)
	private String gstin;
	@Attribute(index=6)
	private int visits;
	@Attribute(index=7)
	private int purchased;
	@Attribute(index=8)
	private int payment_due;
	
	@Override
	public String toString() {
		return "Customer{"+ EntityManager.separator + customer_id + EntityManager.separator + customer_name
				+ EntityManager.separator + address + EntityManager.separator + state + EntityManager.separator
				+ phone_number + EntityManager.separator + email + EntityManager.separator + gstin
				+ EntityManager.separator + visits + EntityManager.separator + purchased + EntityManager.separator
				+ payment_due + EntityManager.separator +"}";
	}
}
