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
public class Vendor {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int vendor_id;
	@Attribute(index=0) @NotNull
	private String vendor_name;
	@Attribute(index=1) @NotNull
	private String address;
	@Attribute(index=2) @NotNull
	private String state;
	@Attribute(index=3)
	private int pincode;
	@Attribute(index=4)
	//TODO string phone in all places of project
	private int phone_number;
	@Attribute(index=5)
	private String email;
	@Attribute(index=6)
	//TODO change to "gstin"
	private String gst_in;
	@Attribute(index=7)
	//TODO bigdecimal
	private int purchased;
	@Attribute(index=8)
	//TODO bigdecimal
	private int payment_due;
	@Override
	public String toString() {
		return "Vendor{"+EntityManager.separator + vendor_id + EntityManager.separator + vendor_name + EntityManager.separator + address + EntityManager.separator + state
				+ EntityManager.separator + pincode + EntityManager.separator + phone_number + EntityManager.separator + email + EntityManager.separator + gst_in + EntityManager.separator
				+ purchased + EntityManager.separator + payment_due + EntityManager.separator+"}";
	}
	
	/*
	@Attribute(index=9)
	@Attribute(index=10)
	@Attribute(index=11)
	@Attribute(index=12)
	@Attribute(index=13)
	@Attribute(index=14)
	@Attribute(index=15)
	@Attribute(index=16)
	@Attribute(index=17)
	@Attribute(index=18)
	@Attribute(index=19)
	@Attribute(index=20)
	@Attribute(index=21)
	@Attribute(index=22)
	private String vat_no;
	private String liscence_no;
	private String mfg_lic_no;
	private String vat_tin_no;
	private String food_lic_no;
	private String business_type;
	private int fax_number;
	private String website;
	private int vat_no_expiry_date;
	private int liscence_no_expiry_date;
	private int mfg_lic_no_expiry_date;
	private int vat_tin_no_expiry_date;
	private int gstin_expiry_date;
	private int food_lic_no_expiry_date;
	*/
/*
	@Override
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
	}*/
}
