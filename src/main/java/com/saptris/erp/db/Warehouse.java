package com.saptris.erp.db;

import static com.saptris.erp.EntityManager.separator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.saptris.erp.annotation.Attribute;
import com.saptris.erp.annotation.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Warehouse{
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int warehouse_id;
//	TODO notnull at all places
	@Attribute(index=0) @NotNull
	private String warehouse_name;
	@Attribute(index=1)
	private String address;
	@Attribute(index=2)
	private String phone;	
	@Attribute(index=3)
	private String incharge_name;	
	@Attribute(index=4)
	private String incharge_phone;
	@Attribute(index=5)
	private String description;
	
	@Override
	public String toString() {
		return "Warehouse{"+ separator + warehouse_id + separator + warehouse_name + separator + address + separator + phone + separator + incharge_name + separator
				+ incharge_phone + separator + description + separator + "}";
	}
}
