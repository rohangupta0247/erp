package com.saptris.erp.db;

import java.math.BigDecimal;
import java.sql.Date;

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

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class ItemTemp /* implements Persistable*/{//TODO change to item and change in dropalltable
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int item_id;
	@Attribute(index=0) @NotNull
	private String item_name;
	@OneToOne @JoinColumn(name="warehouse_id")
	@Attribute(index=1) @NotNull
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
	
	@Override
	public String toString() {
		return "ItemTemp{" + EntityManager.separator +item_id + EntityManager.separator + item_name + EntityManager.separator + warehouse + EntityManager.separator + quantity + EntityManager.separator
				+ cost + EntityManager.separator + arrival_date + EntityManager.separator + hsn + EntityManager.separator +"}";
	}
}
