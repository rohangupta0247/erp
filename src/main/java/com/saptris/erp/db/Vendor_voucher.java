package com.saptris.erp.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.saptris.erp.annotation.Attribute;

@Entity
public class Vendor_voucher {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int voucher_id;
	@Attribute(index=0)
	private int voucher_date;
	@Attribute(index=1)
	private int vendor_id;
	@Attribute(index=2)
	private String vendor_name;
	@Attribute(index=3)
	private int total;
	@Attribute(index=4)
	private int payment_due;
	@Attribute(index=5)
	private String payment_method;
	@Attribute(index=6)
	private int tax_paid;
	@OneToOne @JoinColumn(name="vendor_id")
	@Attribute(index=7)
	private Vendors vendors;

	public Vendor_voucher() {
	}

	public Vendor_voucher(int voucher_id, int voucher_date, int vendor_id,
			String vendor_name, int total, int payment_due,
			String payment_method, int tax_paid, Vendors vendors) {
		super();
		this.voucher_id = voucher_id;
		this.voucher_date = voucher_date;
		this.vendor_id = vendor_id;
		this.vendor_name = vendor_name;
		this.total = total;
		this.payment_due = payment_due;
		this.payment_method = payment_method;
		this.tax_paid = tax_paid;
		this.vendors = vendors;
	}

	public String toString() {
		return "Vendor_voucher{" + voucher_id + "replace" + voucher_date
				+ "replace" + vendor_id + "replace" + vendor_name + "replace"
				+ total + "replace" + payment_due + "replace" + payment_method
				+ "replace" + tax_paid + "replace" + vendors + "}";
	}

	public int getVoucher_id() {
		return voucher_id;
	}

	public void setVoucher_id(int voucher_id) {
		this.voucher_id = voucher_id;
	}

	public int getVoucher_date() {
		return voucher_date;
	}

	public void setVoucher_date(int voucher_date) {
		this.voucher_date = voucher_date;
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

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPayment_due() {
		return payment_due;
	}

	public void setPayment_due(int payment_due) {
		this.payment_due = payment_due;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public int getTax_paid() {
		return tax_paid;
	}

	public void setTax_paid(int tax_paid) {
		this.tax_paid = tax_paid;
	}

	public Vendors getVendors() {
		return vendors;
	}

	public void setVendors(Vendors vendors) {
		this.vendors = vendors;
	}

}
