package com.saptris.erp.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.saptris.erp.annotation.Attribute;

@Entity
public class Staff {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int employee_id;
	@Attribute(index=0)
	private String employee_name;
	@Attribute(index=1)
	private int aadhaar_no;
	@Attribute(index=2)
	private int phone_no;
	@Attribute(index=3)
	private String email;
	@Attribute(index=4)
	private String post;
	@Attribute(index=5)
	private String supervisor;
	@Attribute(index=6)
	private String address;
	@Attribute(index=7)
	private String gender;
	@Attribute(index=8)
	private int d_o_b;
	@Attribute(index=9)
	private String marital_status;
	@Attribute(index=10)
	private String qualification;
	@Attribute(index=11)
	private String experience;
	@Attribute(index=12)
	private String skills;
	@Attribute(index=13)
	private int salary;
	@Attribute(index=14)
	private int start_of_month;

	public Staff() {
	}

	public Staff(int employee_id, String employee_name, int aadhaar_no,
			int phone_no, String email, String post, String supervisor,
			String address, String gender, int d_o_b, String marital_status,
			String qualification, String experience, String skills, int salary,
			int start_of_month) {
		super();
		this.employee_id = employee_id;
		this.employee_name = employee_name;
		this.aadhaar_no = aadhaar_no;
		this.phone_no = phone_no;
		this.email = email;
		this.post = post;
		this.supervisor = supervisor;
		this.address = address;
		this.gender = gender;
		this.d_o_b = d_o_b;
		this.marital_status = marital_status;
		this.qualification = qualification;
		this.experience = experience;
		this.skills = skills;
		this.salary = salary;
		this.start_of_month = start_of_month;
	}

	public String toString() {
		return "Staff{" + employee_id + "replace" + employee_name + "replace"
				+ aadhaar_no + "replace" + phone_no + "replace" + email
				+ "replace" + post + "replace" + supervisor + "replace"
				+ address + "replace" + gender + "replace" + d_o_b + "replace"
				+ marital_status + "replace" + qualification + "replace"
				+ experience + "replace" + skills + "replace" + salary
				+ "replace" + start_of_month + "}";
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public int getAadhaar_no() {
		return aadhaar_no;
	}

	public void setAadhaar_no(int aadhaar_no) {
		this.aadhaar_no = aadhaar_no;
	}

	public int getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(int phone_no) {
		this.phone_no = phone_no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
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

	public int getD_o_b() {
		return d_o_b;
	}

	public void setD_o_b(int d_o_b) {
		this.d_o_b = d_o_b;
	}

	public String getMarital_status() {
		return marital_status;
	}

	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getStart_of_month() {
		return start_of_month;
	}

	public void setStart_of_month(int start_of_month) {
		this.start_of_month = start_of_month;
	}

}
