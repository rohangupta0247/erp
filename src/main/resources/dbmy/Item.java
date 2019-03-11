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
public class Item /*implements Persistable*/{
	//type of data can be a Persistable class / String / int / double
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int item_id;
	@Attribute(index=0)
	private String item_name;
	@OneToOne @JoinColumn(name="warehouse_id")
	@Attribute(index=1)
	private Warehouse warehouse;
	
	@Override
	public String toString() {
		return "Item{"+ separator + item_id + separator + item_name + separator + warehouse + separator + "}";
	}
	public Item() {
	}
	public Item(String item_name, Warehouse warehouse) {
		this.item_name = item_name;
		this.warehouse = warehouse;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public Warehouse getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
}
