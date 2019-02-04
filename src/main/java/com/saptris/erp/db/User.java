package com.saptris.erp.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
//TODO when deploying comment below line and update cfg xml
@Table(name="public.User")
public class User{
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username + ", email=" + email + ", name=" + name
				+ ", phone=" + phone + ", pass=" + pass + "]";
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int user_id;
	@NotNull @Column(unique=true)
	private String username;
	@NotNull
	private String email;
	@NotNull
	private String name;
	//@NotNull
	private String phone;
	@NotNull
	private String pass;
	
	public User() {}
	
	public User(String username, String email, String name, String phone, String pass) {
		this.username = username;
		this.email = email;
		this.name = name;
		this.phone = phone;
		this.pass = pass;
	}
	
	public User(int user_id, String username, String email, String name, String phone, String pass) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.email = email;
		this.name = name;
		this.phone = phone;
		this.pass = pass;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
}
