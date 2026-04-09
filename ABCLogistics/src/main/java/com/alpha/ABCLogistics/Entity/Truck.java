package com.alpha.ABCLogistics.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Truck {
	@Id
	private int id;
	private String name;
	private int number;
	private int capacity;
	private String status;
	@OneToOne
	@JoinColumn(name = "carrier_id")
	private Carrier carrier;

	public Truck() {
		super();
	}

	public Truck(int id, String name, int number, int capacity, String status, Carrier carrier) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.capacity = capacity;
		this.status = status;
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}
}