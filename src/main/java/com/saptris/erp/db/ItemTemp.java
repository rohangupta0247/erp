package com.saptris.erp.db;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.saptris.erp.EntityManager;
import com.saptris.erp.annotation.Attribute;
import com.saptris.erp.annotation.NotNull;

@Entity
public class ItemTemp {
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int item_id;
	@Attribute(index=0)
	@NotNull
	private String item_name;
	@OneToOne @JoinColumn(name="warehouse_id")
	@Attribute(index=1)
	private Warehouse warehouse;
	@Attribute(index=2)
	private int quantity;
	@Attribute(index=3)
	private BigDecimal cost;
	@Attribute(index=4)
	private Date arrival_date;
	@Attribute(index=5)
	@Column(columnDefinition="int default 0")
	private int hsn;
	//private Map<String, String> map= new HashMap<>();
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public Date getArrival_date() {
		return arrival_date;
	}
	public void setArrival_date(Date arrival_date) {
		this.arrival_date = arrival_date;
	}
	/*public Map<String, String> getMap() {
		return map;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}*/
	public ItemTemp() {}
	
	@Override
	public String toString() {
		return "ItemTemp{" + EntityManager.separator +item_id + EntityManager.separator + item_name + EntityManager.separator + warehouse + EntityManager.separator + quantity + EntityManager.separator
				+ cost + EntityManager.separator + arrival_date + EntityManager.separator + hsn + EntityManager.separator +"}";
	}
	public int getHsn() {
		return hsn;
	}
	public void setHsn(int hsn) {
		this.hsn = hsn;
	}
	public ItemTemp(String item_name, Warehouse warehouse, int quantity, BigDecimal cost, Date arrival_date,
			int hsn) {
		this.item_name = item_name;
		this.warehouse = warehouse;
		this.quantity = quantity;
		this.cost = cost;
		this.arrival_date = arrival_date;
		this.hsn = hsn;
	}
	public ItemTemp(String item_name, Warehouse warehouse, int quantity, BigDecimal cost, Date arrival_date,
			int hsn, Map<String, String> map) {
		this.item_name = item_name;
		this.warehouse = warehouse;
		this.quantity = quantity;
		this.cost = cost;
		this.arrival_date = arrival_date;
		this.hsn = hsn;
		//this.map = map;
	}
	
	
}
