package com.saptris.erp.db;

import static com.saptris.erp.EntityManager.separator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.saptris.erp.annotation.Attribute;

@Entity
public class Warehouses {
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int warehouse_id;
	@Attribute(index=0)
	private String warehouse_name;
	@Attribute(index=1)
	private String address;
	@Attribute(index=2)
	private int phone_number;
	@Attribute(index=3)
	private String branch_code;
	@Attribute(index=4)
	private int pincode;
	@Attribute(index=5)
	private String landmark;
	@Attribute(index=6)
	private String incharge_name;
	@Attribute(index=7)
	private int incharge_phone;
	@Attribute(index=8)
	private String description;

	public Warehouses() {
	}

	public Warehouses(int warehouse_id, String warehouse_name, String address,
			int phone_number, String branch_code, int pincode, String landmark,
			String incharge_name, int incharge_phone, String description) {
		super();
		this.warehouse_id = warehouse_id;
		this.warehouse_name = warehouse_name;
		this.address = address;
		this.phone_number = phone_number;
		this.branch_code = branch_code;
		this.pincode = pincode;
		this.landmark = landmark;
		this.incharge_name = incharge_name;
		this.incharge_phone = incharge_phone;
		this.description = description;
	}

	public String toString() {
		return "Warehouses{"+separator + warehouse_id + separator + warehouse_name
				+ separator + address + separator + phone_number + separator
				+ branch_code + separator + pincode + separator + landmark
				+ separator + incharge_name + separator + incharge_phone
				+ separator + description + separator+"}";
	}

	public int getWarehouse_id() {
		return warehouse_id;
	}

	public void setWarehouse_id(int warehouse_id) {
		this.warehouse_id = warehouse_id;
	}

	public String getWarehouse_name() {
		return warehouse_name;
	}

	public void setWarehouse_name(String warehouse_name) {
		this.warehouse_name = warehouse_name;
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

	public String getBranch_code() {
		return branch_code;
	}

	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getIncharge_name() {
		return incharge_name;
	}

	public void setIncharge_name(String incharge_name) {
		this.incharge_name = incharge_name;
	}

	public int getIncharge_phone() {
		return incharge_phone;
	}

	public void setIncharge_phone(int incharge_phone) {
		this.incharge_phone = incharge_phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
