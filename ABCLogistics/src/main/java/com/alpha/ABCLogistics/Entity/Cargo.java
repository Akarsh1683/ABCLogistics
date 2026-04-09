package com.alpha.ABCLogistics.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Cargo {
	@Id
	private int id;
	private String name;
	private String decscription;
	private int weight;
	private int count;
	
	public int getId() {
		return id;
	}
	public Cargo() {
		super();
	}
	public Cargo(int id, String name, String decscription, int weight, int count) {
		super();
		this.id = id;
		this.name = name;
		this.decscription = decscription;
		this.weight = weight;
		this.count = count;
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
	public String getDecscription() {
		return decscription;
	}
	public void setDecscription(String decscription) {
		this.decscription = decscription;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}