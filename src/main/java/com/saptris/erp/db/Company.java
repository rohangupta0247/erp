package com.saptris.erp.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.saptris.erp.EntityManager;
import com.saptris.erp.annotation.Attribute;

@Entity
public class Company {
	//TODO
	//adding new int : @Column(columnDefinition="int default 0")
	//adding new double : 
	//adding new String : 
	//adding new sql Date : 
	//adding new util Date : 
	//adding new Object : 
	
	//deleting or updating column name: not done by Hibernate, needs to be done inside database
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int company_id;
	@Attribute(index=0)
	private String company_name;
	@Attribute(index=1)
	private String address;
	@Attribute(index=2)
	private String phone_number;
	@Attribute(index=3)
	private String email;
	@Attribute(index=4)
	private String website;
	@Attribute(index=5)
	private String jurisdiction;
	@Attribute(index=6)
	private String gstin;
	@Attribute(index=7)
	private String state;
	@Attribute(index=8)
	private int pincode;
	@Attribute(index=9)
	private String country;
	@Attribute(index=10)
	private String bank_name;
	@Attribute(index=11)
	private String account_no;
	@Attribute(index=12)
	private String ifsc_code;
	@Attribute(index=13)
	private String tnc_for_customer;
	@Attribute(index=14)
	private String deals_in;
	@Attribute(index=15)
	private int bank_balance;
	@Attribute(index=16)
	private int cash_balance;
//	@Attribute(index=17)
//	@Attribute(index=18)
//	@Attribute(index=19)
//	@Attribute(index=20)
//	@Attribute(index=21)
//	@Attribute(index=22)
//	@Attribute(index=23)
//	@Attribute(index=24)
//	@Attribute(index=25)
//	@Attribute(index=26)
//	@Attribute(index=27)
//	@Attribute(index=28)
//	@Attribute(index=29)
//	@Attribute(index=30)
//	@Attribute(index=31)
//	@Attribute(index=32)
//	@Attribute(index=33)
//	@Attribute(index=34)
	//private int branch_code;
	//private int fax_number;
	//private String vat_no;
	//private String liscence_no;
	//private String mfg_lic_no;
	//private String vat_tin_no;
	//private String service_tax_no;
	//private String d_l_no;  
	//private String business_type;
	//private int financial_year;
	//private String language;
	//private int gstin_exp_date;
	//private String food_lic_no;
	//private int vat_no_exp_date;
	//private int mfg_lic_no_exp_date;
	//private int lisc_no_exp_date;
	//private int vat_tin_no_exp_date;
	//private int service_tax_no_exp_date;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getJurisdiction() {
		return jurisdiction;
	}
	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	public String getIfsc_code() {
		return ifsc_code;
	}
	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}
	public String getTnc_for_customer() {
		return tnc_for_customer;
	}
	public void setTnc_for_customer(String tnc_for_customer) {
		this.tnc_for_customer = tnc_for_customer;
	}
	public String getDeals_in() {
		return deals_in;
	}
	public void setDeals_in(String deals_in) {
		this.deals_in = deals_in;
	}
	public int getBank_balance() {
		return bank_balance;
	}
	public void setBank_balance(int bank_balance) {
		this.bank_balance = bank_balance;
	}
	public int getCash_balance() {
		return cash_balance;
	}
	public void setCash_balance(int cash_balance) {
		this.cash_balance = cash_balance;
	}
	public Company() {
	}
	public Company(String company_name, String address, String phone_number, String email, String website,
			String jurisdiction, String gstin, String state, int pincode, String country, String bank_name,
			String account_no, String ifsc_code, String tnc_for_customer, String deals_in, int bank_balance,
			int cash_balance) {
		this.company_name = company_name;
		this.address = address;
		this.phone_number = phone_number;
		this.email = email;
		this.website = website;
		this.jurisdiction = jurisdiction;
		this.gstin = gstin;
		this.state = state;
		this.pincode = pincode;
		this.country = country;
		this.bank_name = bank_name;
		this.account_no = account_no;
		this.ifsc_code = ifsc_code;
		this.tnc_for_customer = tnc_for_customer;
		this.deals_in = deals_in;
		this.bank_balance = bank_balance;
		this.cash_balance = cash_balance;
	}
	@Override
	public String toString() {
		return "Company{"+EntityManager.separator + company_id + EntityManager.separator + company_name + EntityManager.separator + address + EntityManager.separator
				+ phone_number + EntityManager.separator + email + EntityManager.separator + website + EntityManager.separator + jurisdiction + EntityManager.separator + gstin
				+ EntityManager.separator + state + EntityManager.separator + pincode + EntityManager.separator + country + EntityManager.separator + bank_name + EntityManager.separator
				+ account_no + EntityManager.separator + ifsc_code + EntityManager.separator + tnc_for_customer + EntityManager.separator + deals_in + EntityManager.separator
				+ bank_balance + EntityManager.separator + cash_balance + EntityManager.separator+"}";
	}
	
	
	
}
