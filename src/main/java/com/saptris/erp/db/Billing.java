package com.saptris.erp.db;

import static com.saptris.erp.EntityManager.separator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.saptris.erp.annotation.Attribute;

@Entity
public class Billing {
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int invoice_id;
	@Attribute(index=0)
	private int invoice_date;
	@Attribute(index=1)
	private int customer_id;
	@Attribute(index=2)
	private String customer_name;
	@Attribute(index=3)
	private int total;
	@Attribute(index=4)
	private int payment_due;
	@Attribute(index=5)
	private int payment_method;
	@Attribute(index=6)
	private int round_off;
	@Attribute(index=7)
	private int tax_acquired;
	@OneToOne @JoinColumn(name="customer_id")
	@Attribute(index=8)
	private Customers customers;
	
	public Billing() {
	}

	public Billing(int invoice_id, int invoice_date, int customer_id,
			String customer_name, int total, int payment_due,
			int payment_method, int round_off, int tax_acquired,
			Customers customers) {
		super();
		this.invoice_id = invoice_id;
		this.invoice_date = invoice_date;
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.total = total;
		this.payment_due = payment_due;
		this.payment_method = payment_method;
		this.round_off = round_off;
		this.tax_acquired = tax_acquired;
		this.customers = customers;
	}

	public String toString() {
		return "Billing{"+ separator + invoice_id + separator + invoice_date + separator
				+ customer_id + separator + customer_name + separator + total
				+ separator + payment_due + separator + payment_method
				+ separator + round_off + separator + tax_acquired + separator +"}";
	}

	public Customers getCustomers() {
		return customers;
	}

	public void setCustomers(Customers customers) {
		this.customers = customers;
	}

	public int getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}

	public int getInvoice_date() {
		return invoice_date;
	}

	public void setInvoice_date(int invoice_date) {
		this.invoice_date = invoice_date;
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

	public int getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(int payment_method) {
		this.payment_method = payment_method;
	}

	public int getRound_off() {
		return round_off;
	}

	public void setRound_off(int round_off) {
		this.round_off = round_off;
	}

	public int getTax_acquired() {
		return tax_acquired;
	}

	public void setTax_acquired(int tax_acquired) {
		this.tax_acquired = tax_acquired;
	}

	

}
