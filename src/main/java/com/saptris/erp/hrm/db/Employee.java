package com.saptris.erp.hrm.db;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.saptris.erp.EntityManager;
import com.saptris.erp.annotation.Attribute;
import com.saptris.erp.db.BankAccount;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Employee {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int employee_id;
	@Attribute(index=0)
	private String name;
	@Attribute(index=1)
	private String phone_no;
	@Attribute(index=2)
	private String email;
	@Attribute(index=3)
	private String address;
	@Attribute(index=4, values= {"Male", "Female", "Others"})
	private String gender;
	@Attribute(index=5)
	//FIXME notnull here or validation for null in payslip 
	private Date date_of_birth;
	@Attribute(index=6, values= {"Married", "Unmarried"})
	private String marital_status;
	@Attribute(index=7)
	private String department;
	@Attribute(index=8)
	private String designation;
	@Attribute(index=9)
	//FIXME notnull here or validation for null in payslip
	private Date date_of_joining;
	@Attribute(index=10)
	@ManyToMany(fetch=FetchType.EAGER) @JoinColumn(name = "payhead_id")
	private Set<Payhead> payhead;
	@Attribute(index=11)
	@OneToOne/*this is causing problem so use beforeDelete()-->(orphanRemoval=true)*/ @JoinColumn(name="account_id")
	private BankAccount bank_account;
	@Attribute(index=12)
	private String pAN_no;
	@Attribute(index=13)
	private String pF_no;
	@Attribute(index=14)
	private String pF_UAN;
	@Attribute(index=15)
	private String eSIC_no;
	@Attribute(index=16)
	private String aadhaar_no;
	@Attribute(index=17)
	private Date date_of_leaving;
	@Attribute(index=18)
	private String reason_of_leaving;
	//TODO work_experience qualification skills
	
	
	@Override
	public String toString() {
		return "Employee{"+EntityManager.separator + employee_id + EntityManager.separator + name + EntityManager.separator + phone_no + EntityManager.separator + email
				+ EntityManager.separator + address + EntityManager.separator + gender + EntityManager.separator + date_of_birth + EntityManager.separator + marital_status
				+ EntityManager.separator + department + EntityManager.separator + designation + EntityManager.separator + date_of_joining + EntityManager.separator
				+ getFormattedPayheadToString() + EntityManager.separator + bank_account + EntityManager.separator + pAN_no + EntityManager.separator + pF_no + EntityManager.separator
				+ pF_UAN + EntityManager.separator + eSIC_no + EntityManager.separator + aadhaar_no + EntityManager.separator + date_of_leaving + EntityManager.separator + reason_of_leaving + EntityManager.separator+"}";
	}
	
	public void beforeDelete() {
		EntityManager em= new EntityManager("BankAccount");
		//for(BankAccount acc: bank_account)
		BankAccount acc= bank_account;
			em.delete(acc.getAccount_id());
	}
	
	private String getFormattedPayheadToString() {
		String res="[";
		int c=0;
		for(Payhead temp: payhead) {
			if(c++!=0)
				res+=", ";
			res+=temp.getPayhead_id();
		}
		res+="]";
		return res;
	}
}
