package com.saptris.erp.db;

import static com.saptris.erp.EntityManager.separator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.saptris.erp.annotation.Attribute;

@Entity
public class Warehouse/* implements Persistable*/{
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int warehouse_id;
//	TODO remove comments
//	@Column(nullable=false)
	@Attribute(index=0)
	private String warehouse_name;
//	@Column(nullable=false)	
	@Attribute(index=1)
	private String address;
//	@Column(nullable=false)	
	@Attribute(index=2)
	private String phone;
//	@Column(nullable=false)	
	@Attribute(index=3)
	private String incharge_name;
//	@Column(nullable=false)	
	@Attribute(index=4)
	private String incharge_phone;
//	@Column(nullable=false)	
	@Attribute(index=5)
	private String description;
	
	
	@Override
	public String toString() {
		return "Warehouse{"+ separator + warehouse_id + separator + warehouse_name + separator + address + separator + phone + separator + incharge_name + separator
				+ incharge_phone + separator + description + separator + "}";
	}

	public Warehouse() {}

	public Warehouse(String warehouse_name, String address, String phone, String incharge_name, String incharge_phone,
			String description) {
		this.warehouse_name = warehouse_name;
		this.address = address;
		this.phone = phone;
		this.incharge_name = incharge_name;
		this.incharge_phone = incharge_phone;
		this.description = description;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIncharge_name() {
		return incharge_name;
	}

	public void setIncharge_name(String incharge_name) {
		this.incharge_name = incharge_name;
	}

	public String getIncharge_phone() {
		return incharge_phone;
	}

	public void setIncharge_phone(String incharge_phone) {
		this.incharge_phone = incharge_phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
