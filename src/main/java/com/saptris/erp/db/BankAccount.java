package com.saptris.erp.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.saptris.erp.EntityManager;
import com.saptris.erp.annotation.Attribute;
import com.saptris.erp.annotation.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class BankAccount {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int account_id;
	@Attribute(index=0) @NotNull
	private String name;//TODO change to bank_name
	@Attribute(index=1) @NotNull
	private String account_no;
	@Attribute(index=2) @NotNull
	private String iFSC_code;
	@Attribute(index=3) @NotNull
	private String branch;
	
	@Override
	public String toString() {
		return "BankAccount{"+EntityManager.separator + account_id + EntityManager.separator + name + EntityManager.separator + account_no + EntityManager.separator + iFSC_code
				+ EntityManager.separator + branch + EntityManager.separator+"}";
	}

}
