package com.saptris.erp.hrm.db;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.saptris.erp.EntityManager;
import com.saptris.erp.annotation.Attribute;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter /*error in hibenrate in this --> @EqualsAndHashCode*/
public class Payhead{
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int payhead_id;
	@Attribute(index=0)
	private String payhead_name;
	@Attribute(index=1)
	private BigDecimal amount/*= new BigDecimal("0")*/;
	@Attribute(index=2)
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(joinColumns = { @JoinColumn(name = "payhead_id") }, inverseJoinColumns = { @JoinColumn(name = "calculated_on_payhead") })
	private Set<Payhead> payhead/*= new HashSet<>()*/;
	@Attribute(index=3, values= {"Earning", "Deduction"})
	private String type;
	@Attribute(index=4, values= {"Fixed", "Variable"})
	private String value;
	//fixed fix me unable to delete this entity in db browser
	@Override
	public String toString() {
		return "Payhead{"+EntityManager.separator + payhead_id + EntityManager.separator + payhead_name + EntityManager.separator + amount
				+ EntityManager.separator + getFormattedPayheadToString() + EntityManager.separator + type + EntityManager.separator+ value + EntityManager.separator+"}";
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((payhead == null) ? 0 : 11*prime/*error in accessing set due to lazy eager loading --> payhead.hashCode()*/);
		result = prime * result + payhead_id;
		result = prime * result + ((payhead_name == null) ? 0 : payhead_name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payhead other = (Payhead) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (payhead == null) {
			if (other.payhead != null)
				return false;
		} else if (!payhead.equals(other.payhead))
			return false;
		if (payhead_id != other.payhead_id)
			return false;
		if (payhead_name == null) {
			if (other.payhead_name != null)
				return false;
		} else if (!payhead_name.equals(other.payhead_name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
