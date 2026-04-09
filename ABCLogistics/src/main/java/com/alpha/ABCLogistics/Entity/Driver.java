
package com.alpha.ABCLogistics.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Driver {
	@Id
	private int id;
	private String name;
	private long contact;
	@OneToOne
	private Truck truck;
	@OneToOne
	private Carrier carrier;
	public Driver() {
		super();
	}
	public Driver(int id, String name, long contact, Truck truck, Carrier carrier) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.truck = truck;
		this.carrier = carrier;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public Truck getTruck() {
		return truck;
	}
	public void setTruck(Truck truck) {
		this.truck = truck;
	}
	public Carrier getCarrier() {
		return carrier;
	}
	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
