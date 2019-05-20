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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="maintenance_all_users")
@Getter @Setter
//this class is mapped in hibernateUser.cfg.xml
//special web page condition for this
//special condition for this when getUserSessionFactory in EntityManager
//special condition for this in EntityManager saveRecord
public class MaintenanceAllUsers {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
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

	@Override
	public String toString() {
		return "MaintenanceAllUsers{"+ separator + maintenance_id + separator + item_name + separator + maintenance_time
				+ separator + description + separator + user + separator + maintainer_name + separator
				+ maintainer_email + separator + maintainer_phone + separator +"}";
	}
}
