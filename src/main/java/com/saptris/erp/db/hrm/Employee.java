package com.saptris.erp.db.hrm;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.saptris.erp.EntityManager;
import com.saptris.erp.annotation.Attribute;
import com.saptris.erp.db.BankAccount;

@Entity
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
	@Attribute(index=4)
	private String gender;
	@Attribute(index=5)
	private Date date_of_birth;
	@Attribute(index=6)
	private String marital_status;
	@Attribute(index=7)
	private String department;
	@Attribute(index=8)
	private String designation;
	@Attribute(index=9)
	private Date date_of_joining;
	@Attribute(index=10)
	@OneToMany @JoinColumn(name="account_id")
	private Set<BankAccount> bank_account;
	@Attribute(index=11)
	private String pAN_no;
	@Attribute(index=12)
	private String pF_no;
	@Attribute(index=13)
	private String pF_UAN;
	@Attribute(index=14)
	private String eSIC_no;
	@Attribute(index=15)
	private String aadhaar_no;
	@Attribute(index=16)
	private Date date_of_leaving;
	@Attribute(index=17)
	private String reason_of_leaving;
	//TODO work_experience qualification skills
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public String getMarital_status() {
		return marital_status;
	}
	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Date getDate_of_joining() {
		return date_of_joining;
	}
	public void setDate_of_joining(Date date_of_joining) {
		this.date_of_joining = date_of_joining;
	}
	public Set<BankAccount> getBank_account() {
		return bank_account;
	}
	public void setBank_account(Set<BankAccount> bank_account) {
		this.bank_account = bank_account;
	}
	public String getpAN_no() {
		return pAN_no;
	}
	public void setpAN_no(String pAN_no) {
		this.pAN_no = pAN_no;
	}
	public String getpF_no() {
		return pF_no;
	}
	public void setpF_no(String pF_no) {
		this.pF_no = pF_no;
	}
	public String getpF_UAN() {
		return pF_UAN;
	}
	public void setpF_UAN(String pF_UAN) {
		this.pF_UAN = pF_UAN;
	}
	public String geteSIC_no() {
		return eSIC_no;
	}
	public void seteSIC_no(String eSIC_no) {
		this.eSIC_no = eSIC_no;
	}
	public String getAadhaar_no() {
		return aadhaar_no;
	}
	public void setAadhaar_no(String aadhaar_no) {
		this.aadhaar_no = aadhaar_no;
	}
	public Date getDate_of_leaving() {
		return date_of_leaving;
	}
	public void setDate_of_leaving(Date date_of_leaving) {
		this.date_of_leaving = date_of_leaving;
	}
	public String getReason_of_leaving() {
		return reason_of_leaving;
	}
	public void setReason_of_leaving(String reason_of_leaving) {
		this.reason_of_leaving = reason_of_leaving;
	}
	public Employee() {
	}
	public Employee(String name, String phone_no, String email, String address, String gender, Date date_of_birth,
			String marital_status, String department, String designation, Date date_of_joining,
			Set<BankAccount> bank_account, String pAN_no, String pF_no, String pF_UAN, String eSIC_no, String aadhaar_no,
			Date date_of_leaving, String reason_of_leaving) {
		this.name = name;
		this.phone_no = phone_no;
		this.email = email;
		this.address = address;
		this.gender = gender;
		this.date_of_birth = date_of_birth;
		this.marital_status = marital_status;
		this.department = department;
		this.designation = designation;
		this.date_of_joining = date_of_joining;
		this.bank_account = bank_account;
		this.pAN_no = pAN_no;
		this.pF_no = pF_no;
		this.pF_UAN = pF_UAN;
		this.eSIC_no = eSIC_no;
		this.aadhaar_no = aadhaar_no;
		this.date_of_leaving = date_of_leaving;
		this.reason_of_leaving = reason_of_leaving;
	}
	@Override
	public String toString() {
		return "Employee{"+EntityManager.separator + employee_id + EntityManager.separator + name + EntityManager.separator + phone_no + EntityManager.separator + email
				+ EntityManager.separator + address + EntityManager.separator + gender + EntityManager.separator + date_of_birth + EntityManager.separator + marital_status
				+ EntityManager.separator + department + EntityManager.separator + designation + EntityManager.separator + date_of_joining + EntityManager.separator
				+ bank_account + EntityManager.separator + pAN_no + EntityManager.separator + pF_no + EntityManager.separator + pF_UAN + EntityManager.separator + eSIC_no
				+ EntityManager.separator + aadhaar_no + EntityManager.separator + date_of_leaving + EntityManager.separator + reason_of_leaving + EntityManager.separator+"}";
	}
	
	
	public void beforeDelete() {
		EntityManager em= new EntityManager("BankAccount");
		for(BankAccount acc: bank_account) {
			em.delete(acc.getAccount_id());
		}
	}
}
