package com.saptris.erp.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.saptris.erp.annotation.Attribute;

@Entity
public class Customers {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int customer_id;
	@Attribute(index=0)
	private String customer_name;
	@Attribute(index=1)
	private String address;
	@Attribute(index=2)
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

	public Customers() {
	}

	public Customers(int customer_id, String customer_name, String address,
			String state, int phone_number, String email, String gstin,
			int visits, int purchased, int payment_due) {
		super();
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.address = address;
		this.state = state;
		this.phone_number = phone_number;
		this.email = email;
		this.gstin = gstin;
		this.visits = visits;
		this.purchased = purchased;
		this.payment_due = payment_due;
	}

	public String toString() {
		return "Customers{" + customer_id + "replace" + customer_name
				+ "replace" + address + "replace" + state + "replace"
				+ phone_number + "replace" + email + "replace" + gstin
				+ "replace" + visits + "replace" + purchased + "replace"
				+ payment_due + "}";
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(int phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public int getVisits() {
		return visits;
	}

	public void setVisits(int visits) {
		this.visits = visits;
	}

	public int getPurchased() {
		return purchased;
	}

	public void setPurchased(int purchased) {
		this.purchased = purchased;
	}

	public int getPayment_due() {
		return payment_due;
	}

	public void setPayment_due(int payment_due) {
		this.payment_due = payment_due;
	}

}
