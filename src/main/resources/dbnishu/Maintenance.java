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
public class Maintenance {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int maintenance_id;
	@Attribute(index=0)
	private String item_name;
	@Attribute(index=1)
	private int maintenance_date;
	@Attribute(index=2)
	private int cost;
	@Attribute(index=3)
	private String description;
	@Attribute(index=4)
	private int item_id;
	@OneToOne @JoinColumn(name="item_id")
	@Attribute(index=5)
	private Inventory inventory;
	@Attribute(index=6)
	private String maintainer_name;
	@Attribute(index=7)
	private String maintainer_email;
	@Attribute(index=8)
	private String maintainer_phone;

	public Maintenance() {
	}

	public Maintenance(int maintenance_id, String item_name,
			int maintenance_date, int cost, String description, int item_id,
			Inventory inventory, String maintainer_name, String maintainer_email, String maintainer_phone) {
		this.maintenance_id = maintenance_id;
		this.item_name = item_name;
		this.maintenance_date = maintenance_date;
		this.cost = cost;
		this.description = description;
		this.item_id = item_id;
		this.inventory = inventory;
		this.maintainer_name = maintainer_name;
		this.maintainer_email = maintainer_email;
		this.maintainer_phone = maintainer_phone;
	}

	public String toString() {
		return "Maintenance{"+ separator + maintenance_id + separator + item_name
				+ separator + maintenance_date + separator + cost + separator
				+ description + separator + item_id + separator + inventory + separator
				+ maintainer_name + separator + maintainer_email + separator + maintainer_phone + separator + "}";
	}

	public int getMaintenance_id() {
		return maintenance_id;
	}

	public void setMaintenance_id(int maintenance_id) {
		this.maintenance_id = maintenance_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public int getMaintenance_date() {
		return maintenance_date;
	}

	public void setMaintenence_date(int maintenance_date) {
		this.maintenance_date = maintenance_date;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

}
