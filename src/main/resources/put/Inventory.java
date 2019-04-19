package com.saptris.erp.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.saptris.erp.annotation.Attribute;

@Entity
public class Inventory {
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int item_id;
	@Attribute(index=0)
	private String item_name;
	@Attribute(index=1)
	private String description;
	@Attribute(index=2)
	private String category;
	@Attribute(index=3)
	private int unit_size;
	@Attribute(index=4)
	private String vendor_name;
	@Attribute(index=5)
	private int dimensions;
	@Attribute(index=6)
	private int gst_prcent;
	@Attribute(index=7)
	private int cgst_percent;
	@Attribute(index=8)
	private int sgst_percent;
	@Attribute(index=9)
	private int cost_price;
	@Attribute(index=10)
	private int selling_price;
	@Attribute(index=11)
	private int discount_percent;
	@Attribute(index=12)
	private String arrival_date;
	@Attribute(index=13)
	private String expiry_date;
	@Attribute(index=14)
	private String warehouse_name;
	@Attribute(index=15)
	private int quantity_available;
	@Attribute(index=16)
	private int hsn_sac_code;
	@Attribute(index=17)
	private int igst_percent;
	@Attribute(index=18)
	private int quantity_sold;
	@OneToOne @JoinColumn(name="vendor_name")
	@Attribute(index=19)
	private Vendors vendors;
	@OneToOne @JoinColumn(name="warehouse_name")
	@Attribute(index=20)
	private Warehouses warehouses;

	public Inventory() {
	}

	public Inventory(int item_id, String item_name, String description,
			String category, int unit_size, String vendor_name, int dimensions,
			int gst_prcent, int cgst_percent, int sgst_percent, int cost_price,
			int selling_price, int discount_percent, String arrival_date,
			String expiry_date, String warehouse_name, int quantity_available,
			int hsn_sac_code, int igst_percent, int quantity_sold,
			Vendors vendors, Warehouses warehouses) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.description = description;
		this.category = category;
		this.unit_size = unit_size;
		this.vendor_name = vendor_name;
		this.dimensions = dimensions;
		this.gst_prcent = gst_prcent;
		this.cgst_percent = cgst_percent;
		this.sgst_percent = sgst_percent;
		this.cost_price = cost_price;
		this.selling_price = selling_price;
		this.discount_percent = discount_percent;
		this.arrival_date = arrival_date;
		this.expiry_date = expiry_date;
		this.warehouse_name = warehouse_name;
		this.quantity_available = quantity_available;
		this.hsn_sac_code = hsn_sac_code;
		this.igst_percent = igst_percent;
		this.quantity_sold = quantity_sold;
		this.vendors = vendors;
		this.warehouses = warehouses;
	}

	public String toString() {
		return "Inventory{" + item_id + "replace" + item_name + "replace"
				+ description + "replace" + category + "replace" + unit_size
				+ "replace" + vendor_name + "replace" + dimensions + "replace"
				+ gst_prcent + "replace" + cgst_percent + "replace"
				+ sgst_percent + "replace" + cost_price + "replace"
				+ selling_price + "replace" + discount_percent + "replace"
				+ arrival_date + "replace" + expiry_date + "replace"
				+ warehouse_name + "replace" + quantity_available + "replace"
				+ hsn_sac_code + "replace" + igst_percent + "replace"
				+ quantity_sold + "replace" + vendors + "replace" + warehouses
				+ "}";
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getUnit_size() {
		return unit_size;
	}

	public void setUnit_size(int unit_size) {
		this.unit_size = unit_size;
	}

	public String getVendor_name() {
		return vendor_name;
	}

	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}

	public int getDimensions() {
		return dimensions;
	}

	public void setDimensions(int dimensions) {
		this.dimensions = dimensions;
	}

	public int getGst_prcent() {
		return gst_prcent;
	}

	public void setGst_prcent(int gst_prcent) {
		this.gst_prcent = gst_prcent;
	}

	public int getCgst_percent() {
		return cgst_percent;
	}

	public void setCgst_percent(int cgst_percent) {
		this.cgst_percent = cgst_percent;
	}

	public int getSgst_percent() {
		return sgst_percent;
	}

	public void setSgst_percent(int sgst_percent) {
		this.sgst_percent = sgst_percent;
	}

	public int getCost_price() {
		return cost_price;
	}

	public void setCost_price(int cost_price) {
		this.cost_price = cost_price;
	}

	public int getSelling_price() {
		return selling_price;
	}

	public void setSelling_price(int selling_price) {
		this.selling_price = selling_price;
	}

	public int getDiscount_percent() {
		return discount_percent;
	}

	public void setDiscount_percent(int discount_percent) {
		this.discount_percent = discount_percent;
	}

	public String getArrival_date() {
		return arrival_date;
	}

	public void setArrival_date(String arrival_date) {
		this.arrival_date = arrival_date;
	}

	public String getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}

	public String getWarehouse_name() {
		return warehouse_name;
	}

	public void setWarehouse_name(String warehouse_name) {
		this.warehouse_name = warehouse_name;
	}

	public int getQuantity_available() {
		return quantity_available;
	}

	public void setQuantity_available(int quantity_available) {
		this.quantity_available = quantity_available;
	}

	public int getHsn_sac_code() {
		return hsn_sac_code;
	}

	public void setHsn_sac_code(int hsn_sac_code) {
		this.hsn_sac_code = hsn_sac_code;
	}

	public int getIgst_percent() {
		return igst_percent;
	}

	public void setIgst_percent(int igst_percent) {
		this.igst_percent = igst_percent;
	}

	public int getQuantity_sold() {
		return quantity_sold;
	}

	public void setQuantity_sold(int quantity_sold) {
		this.quantity_sold = quantity_sold;
	}

	public Vendors getVendors() {
		return vendors;
	}

	public void setVendors(Vendors vendors) {
		this.vendors = vendors;
	}

	public Warehouses getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(Warehouses warehouses) {
		this.warehouses = warehouses;
	}

}
