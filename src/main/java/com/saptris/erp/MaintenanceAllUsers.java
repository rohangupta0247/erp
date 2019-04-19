package com.saptris.erp;

import static com.saptris.erp.EntityManager.separator;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.saptris.erp.User;
import com.saptris.erp.annotation.Attribute;

@Entity
@Table(name="maintenance_all_users")
//this class is mapped in hibernateUser.cfg.xml
//special web page condition for this
//special condition for this when getUserSessionFactory in EntityManager
//special condition for this in EntityManager saveRecord
public class MaintenanceAllUsers {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int maintenance_id;
	@Attribute(index=0)
	private String item_name;
	@Attribute(index=1)
	private Date maintenance_time;
	@Attribute(index=2)
	private String description;
	@OneToOne @JoinColumn(name="username")
	@Attribute(index=3)
	private User user;
	@Attribute(index=4)
	private String maintainer_name;
	@Attribute(index=5)
	private String maintainer_email;
	@Attribute(index=6)
	private String maintainer_phone;

	public MaintenanceAllUsers() {
	}

	public MaintenanceAllUsers(String item_name, Date maintenance_time, String description, User user,
			String maintainer_name, String maintainer_email, String maintainer_phone) {
		this.item_name = item_name;
		this.maintenance_time = maintenance_time;
		this.description = description;
		this.user = user;
		this.maintainer_name = maintainer_name;
		this.maintainer_email = maintainer_email;
		this.maintainer_phone = maintainer_phone;
	}

	@Override
	public String toString() {
		return "MaintenanceAllUsers{"+ separator + maintenance_id + separator + item_name + separator + maintenance_time
				+ separator + description + separator + user + separator + maintainer_name + separator
				+ maintainer_email + separator + maintainer_phone + separator +"}";
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

	public Date getMaintenance_time() {
		return maintenance_time;
	}

	public void setMaintenance_time(Date maintenance_time) {
		this.maintenance_time = maintenance_time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMaintainer_name() {
		return maintainer_name;
	}

	public void setMaintainer_name(String maintainer_name) {
		this.maintainer_name = maintainer_name;
	}

	public String getMaintainer_email() {
		return maintainer_email;
	}

	public void setMaintainer_email(String maintainer_email) {
		this.maintainer_email = maintainer_email;
	}

	public String getMaintainer_phone() {
		return maintainer_phone;
	}

	public void setMaintainer_phone(String maintainer_phone) {
		this.maintainer_phone = maintainer_phone;
	}
}
