package com.saptris.erp.db.hrm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.saptris.erp.annotation.Attribute;

@Entity
public class VariablePayhead {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int payhead_id;
	@Attribute(index=0)
	private String name;
	@Attribute(index=1)
	private String type;
}
