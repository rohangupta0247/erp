package com.saptris.erp.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.saptris.erp.EntityManager;
import com.saptris.erp.annotation.Attribute;

@Entity
public class BankAccount {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int account_id;
	@Attribute(index=0)
	private String name;
	@Attribute(index=1)
	private String account_no;
	@Attribute(index=2)
	private String iFSC_code;
	@Attribute(index=3)
	private String branch;
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	public String getiFSC_code() {
		return iFSC_code;
	}
	public void setiFSC_code(String iFSC_code) {
		this.iFSC_code = iFSC_code;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public BankAccount() {
	}
	public BankAccount(String name, String account_no, String iFSC_code, String branch) {
		this.name = name;
		this.account_no = account_no;
		this.iFSC_code = iFSC_code;
		this.branch = branch;
	}
	@Override
	public String toString() {
		return "BankAccount{"+EntityManager.separator + account_id + EntityManager.separator + name + EntityManager.separator + account_no + EntityManager.separator + iFSC_code
				+ EntityManager.separator + branch + EntityManager.separator+"}";
	}

}
