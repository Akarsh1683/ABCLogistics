package com.alpha.ABCLogistics.Entity;

import org.hibernate.validator.constraints.Email;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Carrier {
	@Id
	@NotNull
	@Positive
	private int id;
	private String name;
//	@SuppressWarnings("deprecation")
	@Email
	private String mail;
	@NotNull
	@Positive
	private long contact;

	public Carrier() {
	}

	public Carrier(int id, String name, String mail, long contact) {
		this.id = id;
		this.name = name;
		this.mail = mail;
		this.contact = contact;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}
}