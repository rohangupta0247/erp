package com.saptris.erp.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.saptris.erp.EntityManager;
import com.saptris.erp.annotation.Attribute;

@Entity
public class Vendors {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int vendor_id;
	@Attribute(index=0)
	private String vendor_name;
	@Attribute(index=1)
	private String address;
	@Attribute(index=2)
	private int phone_number;
	@Attribute(index=3)
	private int pincode;
	@Attribute(index=4)
	private int fax_number;
	@Attribute(index=5)
	private String website;
	@Attribute(index=6)
	private String email;
	@Attribute(index=7)
	private String state;
	@Attribute(index=8)
	private String vat_no;
	@Attribute(index=9)
	private String liscence_no;
	@Attribute(index=10)
	private String mfg_lic_no;
	@Attribute(index=11)
	private String vat_tin_no;
	@Attribute(index=12)
	private String gst_in;
	@Attribute(index=13)
	private String food_lic_no;
	@Attribute(index=14)
	private String business_type;
	@Attribute(index=15)
	private int purchased;
	@Attribute(index=16)
	private int payment_due;
	@Attribute(index=17)
	private int vat_no_expiry_date;
	@Attribute(index=18)
	private int liscence_no_expiry_date;
	@Attribute(index=19)
	private int mfg_lic_no_expiry_date;
	@Attribute(index=20)
	private int vat_tin_no_expiry_date;
	@Attribute(index=21)
	private int gstin_expiry_date;
	@Attribute(index=22)
	private int food_lic_no_expiry_date;
	
	public Vendors() {
	}

	public Vendors(/*int vendor_id, */String vendor_name, String address,
			int phone_number, int pincode, int fax_number, String website,
			String email, String state, String vat_no, String liscence_no,
			String mfg_lic_no, String vat_tin_no, String gst_in,
			String food_lic_no, String business_type, int purchased,
			int payment_due, int vat_no_expiry_date,
			int liscence_no_expiry_date, int mfg_lic_no_expiry_date,
			int vat_tin_no_expiry_date, int gstin_expiry_date,
			int food_lic_no_expiry_date) {
		//super();
		//this.vendor_id = vendor_id;
		this.vendor_name = vendor_name;
		this.address = address;
		this.phone_number = phone_number;
		this.pincode = pincode;
		this.fax_number = fax_number;
		this.website = website;
		this.email = email;
		this.state = state;
		this.vat_no = vat_no;
		this.liscence_no = liscence_no;
		this.mfg_lic_no = mfg_lic_no;
		this.vat_tin_no = vat_tin_no;
		this.gst_in = gst_in;
		this.food_lic_no = food_lic_no;
		this.business_type = business_type;
		this.purchased = purchased;
		this.payment_due = payment_due;
		this.vat_no_expiry_date = vat_no_expiry_date;
		this.liscence_no_expiry_date = liscence_no_expiry_date;
		this.mfg_lic_no_expiry_date = mfg_lic_no_expiry_date;
		this.vat_tin_no_expiry_date = vat_tin_no_expiry_date;
		this.gstin_expiry_date = gstin_expiry_date;
		this.food_lic_no_expiry_date = food_lic_no_expiry_date;
	}

	public String toString() {
		return "Vendors{"+EntityManager.separator + vendor_id + EntityManager.separator + vendor_name + EntityManager.separator
				+ address + EntityManager.separator + phone_number + EntityManager.separator + pincode
				+ EntityManager.separator + fax_number + EntityManager.separator + website + EntityManager.separator
				+ email + EntityManager.separator + state + EntityManager.separator + vat_no + EntityManager.separator
				+ liscence_no + EntityManager.separator + mfg_lic_no + EntityManager.separator + vat_tin_no
				+ EntityManager.separator + gst_in + EntityManager.separator + food_lic_no + EntityManager.separator
				+ business_type + EntityManager.separator + purchased + EntityManager.separator
				+ payment_due + EntityManager.separator + vat_no_expiry_date + EntityManager.separator
				+ liscence_no_expiry_date + EntityManager.separator + mfg_lic_no_expiry_date
				+ EntityManager.separator + vat_tin_no_expiry_date + EntityManager.separator
				+ gstin_expiry_date + EntityManager.separator + food_lic_no_expiry_date +EntityManager.separator+ "}";
	}

	public int getVendor_id() {
		return vendor_id;
	}

	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
	}

	public String getVendor_name() {
		return vendor_name;
	}

	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
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

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getGst_in() {
		return gst_in;
	}

	public void setGst_in(String gst_in) {
		this.gst_in = gst_in;
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

	public int getVat_no_expiry_date() {
		return vat_no_expiry_date;
	}

	public void setVat_no_expiry_date(int vat_no_expiry_date) {
		this.vat_no_expiry_date = vat_no_expiry_date;
	}

	public int getLiscence_no_expiry_date() {
		return liscence_no_expiry_date;
	}

	public void setLiscence_no_expiry_date(int liscence_no_expiry_date) {
		this.liscence_no_expiry_date = liscence_no_expiry_date;
	}

	public int getMfg_lic_no_expiry_date() {
		return mfg_lic_no_expiry_date;
	}

	public void setMfg_lic_no_expiry_date(int mfg_lic_no_expiry_date) {
		this.mfg_lic_no_expiry_date = mfg_lic_no_expiry_date;
	}

	public int getVat_tin_no_expiry_date() {
		return vat_tin_no_expiry_date;
	}

	public void setVat_tin_no_expiry_date(int vat_tin_no_expiry_date) {
		this.vat_tin_no_expiry_date = vat_tin_no_expiry_date;
	}

	public int getGstin_expiry_date() {
		return gstin_expiry_date;
	}

	public void setGstin_expiry_date(int gstin_expiry_date) {
		this.gstin_expiry_date = gstin_expiry_date;
	}

	public int getFood_lic_no_expiry_date() {
		return food_lic_no_expiry_date;
	}

	public void setFood_lic_no_expiry_date(int food_lic_no_expiry_date) {
		this.food_lic_no_expiry_date = food_lic_no_expiry_date;
	}

}
