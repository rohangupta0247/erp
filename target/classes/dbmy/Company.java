package com.saptris.erp.db;

import static com.saptris.erp.EntityManager.separator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.saptris.erp.annotation.Attribute;

@Entity
public class Company {
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int company_id;
//	TODO remove comments
//	@Column(nullable=false)
	@Attribute(index = 0)
	private String company_name;
//	@Column(nullable=false)
	@Attribute(index = 1)
	private String phone;
//	@Column(nullable=false)
	@Attribute(index = 2)
	private String email;
//	@Column(nullable=false)
	@Attribute(index = 3)
	private String gstin;
//	@Column(nullable=false)
	@Attribute(index = 4)
	private String address;
//	@Column(nullable=false)
	@Attribute(index = 5)
	private String state;
	
	
	public Company() {}
	
	@Override
	public String toString() {
		return "Company{" + separator + company_id + separator + company_name + separator + phone + separator + email + separator + gstin + separator + address + separator + state + separator + "}";
	}

	public Company(String company_name, String phone, String email, String gstin, String address, String state) {
		this.company_name = company_name;
		this.phone = phone;
		this.email = email;
		this.gstin = gstin;
		this.address = address;
		this.state = state;
	}

	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
}
