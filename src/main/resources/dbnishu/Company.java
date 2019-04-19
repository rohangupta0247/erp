package com.saptris.erp.db;

import static com.saptris.erp.EntityManager.separator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private int phone_number;
	@Attribute(index=3)
	private int branch_code;
	@Attribute(index=4)
	private int fax_number;
	@Attribute(index=5)
	private String website;
	@Attribute(index=6)
	private String email;
	@Attribute(index=7)
	private String jurisdiction;
	@Attribute(index=8)
	private String vat_no;
	@Attribute(index=9)
	private String liscence_no;
	@Attribute(index=10)
	private String mfg_lic_no;
	@Attribute(index=11)
	private String vat_tin_no;
	@Attribute(index=12)
	private String gstin;
	@Attribute(index=13)
	private String service_tax_no;
	@Attribute(index=14)
	private String d_l_no;  
	@Attribute(index=15)
	private String food_lic_no;
	@Attribute(index=16)
	private String business_type;
	@Attribute(index=17)
	private int financial_year;
	@Attribute(index=18)
	private String state;
	@Attribute(index=19)
	private int pincode;
	@Attribute(index=20)
	private String country;
	@Attribute(index=21)
	private String language;
	@Attribute(index=22)
	private int vat_no_exp_date;
	@Attribute(index=23)
	private int lisc_no_exp_date;
	@Attribute(index=24)
	private int mfg_lic_no_exp_date;
	@Attribute(index=25)
	private int vat_tin_no_exp_date;
	@Attribute(index=26)
	private int gstin_exp_date;
	@Attribute(index=27)
	private int service_tax_no_exp_date;
	@Attribute(index=28)
	private String bank_name;
	@Attribute(index=29)
	private String account_no;
	@Attribute(index=30)
	private String ifsc_code;
	@Attribute(index=31)
	private String tnc_for_customer;
	@Attribute(index=32)
	private String deals_in;
	@Attribute(index=33)
	private int bank_balance;
	@Attribute(index=34)
	private int cash_balance;
	
	public String toString() {
		return "Company{" +separator+ company_id + separator + company_name + separator
				+ address + separator + phone_number + separator + branch_code
				+ separator + fax_number + separator + website + separator
				+ email + separator + jurisdiction + separator + vat_no
				+ separator + liscence_no + separator + mfg_lic_no + separator
				+ vat_tin_no + separator + gstin + separator + service_tax_no
				+ separator + d_l_no + separator + food_lic_no + separator
				+ business_type + separator + financial_year + separator
				+ state + separator + pincode + separator + country + separator
				+ language + separator + vat_no_exp_date + separator
				+ lisc_no_exp_date + separator + mfg_lic_no_exp_date
				+ separator + vat_tin_no_exp_date + separator + gstin_exp_date
				+ separator + service_tax_no_exp_date + separator + bank_name
				+ separator + account_no + separator + ifsc_code + separator
				+ tnc_for_customer + separator + deals_in + separator
				+ bank_balance + separator + cash_balance + separator+"}";
	}
	public Company(){
	}
	public Company(String company_name, String address, int phone_number,
			int branch_code, int fax_number, String website, String email,
			String jurisdiction, String vat_no, String liscence_no,
			String mfg_lic_no, String vat_tin_no, String gstin,
			String service_tax_no, String d_l_no, String food_lic_no,
			String business_type, int financial_year, String state,
			int pincode, String country, String language, int vat_no_exp_date,
			int lisc_no_exp_date, int mfg_lic_no_exp_date,
			int vat_tin_no_exp_date, int gstin_exp_date,
			int service_tax_no_exp_date, String bank_name, String account_no,
			String ifsc_code, String tnc_for_customer, String deals_in,
			int bank_balance, int cash_balance) {
		super();
		this.company_name = company_name;
		this.address = address;
		this.phone_number = phone_number;
		this.branch_code = branch_code;
		this.fax_number = fax_number;
		this.website = website;
		this.email = email;
		this.jurisdiction = jurisdiction;
		this.vat_no = vat_no;
		this.liscence_no = liscence_no;
		this.mfg_lic_no = mfg_lic_no;
		this.vat_tin_no = vat_tin_no;
		this.gstin = gstin;
		this.service_tax_no = service_tax_no;
		this.d_l_no = d_l_no;
		this.food_lic_no = food_lic_no;
		this.business_type = business_type;
		this.financial_year = financial_year;
		this.state = state;
		this.pincode = pincode;
		this.country = country;
		this.language = language;
		this.vat_no_exp_date = vat_no_exp_date;
		this.lisc_no_exp_date = lisc_no_exp_date;
		this.mfg_lic_no_exp_date = mfg_lic_no_exp_date;
		this.vat_tin_no_exp_date = vat_tin_no_exp_date;
		this.gstin_exp_date = gstin_exp_date;
		this.service_tax_no_exp_date = service_tax_no_exp_date;
		this.bank_name = bank_name;
		this.account_no = account_no;
		this.ifsc_code = ifsc_code;
		this.tnc_for_customer = tnc_for_customer;
		this.deals_in = deals_in;
		this.bank_balance = bank_balance;
		this.cash_balance = cash_balance;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(int phone_number) {
		this.phone_number = phone_number;
	}
	public int getBranch_code() {
		return branch_code;
	}
	public void setBranch_code(int branch_code) {
		this.branch_code = branch_code;
	}
	public int getFax_number() {
		return fax_number;
	}
	public void setFax_number(int fax_number) {
		this.fax_number = fax_number;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJurisdiction() {
		return jurisdiction;
	}
	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
	public String getVat_no() {
		return vat_no;
	}
	public void setVat_no(String vat_no) {
		this.vat_no = vat_no;
	}
	public String getLiscence_no() {
		return liscence_no;
	}
	public void setLiscence_no(String liscence_no) {
		this.liscence_no = liscence_no;
	}
	public String getMfg_lic_no() {
		return mfg_lic_no;
	}
	public void setMfg_lic_no(String mfg_lic_no) {
		this.mfg_lic_no = mfg_lic_no;
	}
	public String getVat_tin_no() {
		return vat_tin_no;
	}
	public void setVat_tin_no(String vat_tin_no) {
		this.vat_tin_no = vat_tin_no;
	}
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	public String getService_tax_no() {
		return service_tax_no;
	}
	public void setService_tax_no(String service_tax_no) {
		this.service_tax_no = service_tax_no;
	}
	public String getD_l_no() {
		return d_l_no;
	}
	public void setD_l_no(String d_l_no) {
		this.d_l_no = d_l_no;
	}
	public String getFood_lic_no() {
		return food_lic_no;
	}
	public void setFood_lic_no(String food_lic_no) {
		this.food_lic_no = food_lic_no;
	}
	public String getBusiness_type() {
		return business_type;
	}
	public void setBusiness_type(String business_type) {
		this.business_type = business_type;
	}
	public int getFinancial_year() {
		return financial_year;
	}
	public void setFinancial_year(int financial_year) {
		this.financial_year = financial_year;
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
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getVat_no_exp_date() {
		return vat_no_exp_date;
	}
	public void setVat_no_exp_date(int vat_no_exp_date) {
		this.vat_no_exp_date = vat_no_exp_date;
	}
	public int getLisc_no_exp_date() {
		return lisc_no_exp_date;
	}
	public void setLisc_no_exp_date(int lisc_no_exp_date) {
		this.lisc_no_exp_date = lisc_no_exp_date;
	}
	public int getMfg_lic_no_exp_date() {
		return mfg_lic_no_exp_date;
	}
	public void setMfg_lic_no_exp_date(int mfg_lic_no_exp_date) {
		this.mfg_lic_no_exp_date = mfg_lic_no_exp_date;
	}
	public int getVat_tin_no_exp_date() {
		return vat_tin_no_exp_date;
	}
	public void setVat_tin_no_exp_date(int vat_tin_no_exp_date) {
		this.vat_tin_no_exp_date = vat_tin_no_exp_date;
	}
	public int getGstin_exp_date() {
		return gstin_exp_date;
	}
	public void setGstin_exp_date(int gstin_exp_date) {
		this.gstin_exp_date = gstin_exp_date;
	}
	public int getService_tax_no_exp_date() {
		return service_tax_no_exp_date;
	}
	public void setService_tax_no_exp_date(int service_tax_no_exp_date) {
		this.service_tax_no_exp_date = service_tax_no_exp_date;
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
	
}
