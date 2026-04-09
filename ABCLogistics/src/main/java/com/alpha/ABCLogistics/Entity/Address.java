
package com.alpha.ABCLogistics.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Address {
	@Id
	@Positive
	@NotNull
	@Min(100)
	private int id;
	@NotNull
	private String street;
	@NotNull
	private String city;
	@Max(999999)
	@Min(100000)
	@Positive
	@NotNull
	private int pincode;
	@NotNull
	private String state;
	
	public Address() {
		super();
	}

	public Address(int id, String street, String city, int pincode, String state) {
		super();
		this.id = id;
		this.street = street;
		this.city = city;
		this.pincode = pincode;
		this.state = state;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
