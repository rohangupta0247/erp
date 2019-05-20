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
public class Company {
	//TODO
	//adding new int : @Column(columnDefinition="int default 0")
	//adding new double : 
	//adding new String : 
	//adding new sql Date : 
	//adding new util Date : 
	//adding new Object : 
	
	//deleting or updating column name: not done by Hibernate, needs to be done inside database
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int company_id;
	@Attribute(index=0) @NotNull
	private String company_name;
	@Attribute(index=1) @NotNull
	private String phone_number;
	@Attribute(index=2) @NotNull
	private String email;
	@Attribute(index=3)
	private String website;
	@Attribute(index=4) @NotNull
	private String address;
	@Attribute(index=5) @NotNull
	private String state;
	@Attribute(index=6) @NotNull
	private int pincode;
	@Attribute(index=7) @NotNull
	private String country;
	@Attribute(index=8) @NotNull
	private String gstin;
	@Attribute(index=9) @NotNull
	private String jurisdiction;
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
	@Attribute(index=15)//TODO bigdecimal
	private int bank_balance;
	@Attribute(index=16)
	private int cash_balance;
	
	@Override
	public String toString() {
		return "Company{"+EntityManager.separator + company_id + EntityManager.separator + company_name + EntityManager.separator + phone_number + EntityManager.separator + email
				+ EntityManager.separator + website + EntityManager.separator + address + EntityManager.separator + state + EntityManager.separator + pincode + EntityManager.separator
				+ country + EntityManager.separator + gstin + EntityManager.separator + jurisdiction + EntityManager.separator + bank_name + EntityManager.separator
				+ account_no + EntityManager.separator + ifsc_code + EntityManager.separator + tnc_for_customer + EntityManager.separator + deals_in + EntityManager.separator
				+ bank_balance + EntityManager.separator + cash_balance + EntityManager.separator+"}";
	}
	
	/*
	@Attribute(index=17)
	@Attribute(index=18)
	@Attribute(index=19)
	@Attribute(index=20)
	@Attribute(index=21)
	@Attribute(index=22)
	@Attribute(index=23)
	@Attribute(index=24)
	@Attribute(index=25)
	@Attribute(index=26)
	@Attribute(index=27)
	@Attribute(index=28)
	@Attribute(index=29)
	@Attribute(index=30)
	@Attribute(index=31)
	@Attribute(index=32)
	@Attribute(index=33)
	@Attribute(index=34)
	private int branch_code;
	private int fax_number;
	private String vat_no;
	private String liscence_no;
	private String mfg_lic_no;
	private String vat_tin_no;
	private String service_tax_no;
	private String d_l_no;  
	private String business_type;
	private int financial_year;
	private String language;
	private int gstin_exp_date;
	private String food_lic_no;
	private int vat_no_exp_date;
	private int mfg_lic_no_exp_date;
	private int lisc_no_exp_date;
	private int vat_tin_no_exp_date;
	private int service_tax_no_exp_date;
	*/
}
