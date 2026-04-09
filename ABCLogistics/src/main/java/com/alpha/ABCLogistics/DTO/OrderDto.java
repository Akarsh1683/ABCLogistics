package com.alpha.ABCLogistics.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderDto {
	private int id;
	private String orderdate;
	private int cargoId;
	private String cargoName;
	private String cargoDescription;
	private String mail;
	
	@NotNull
	@Positive
	private int cargoWt;
	@NotNull
	@Positive
	@Min(1)
	private int cargoCount;
	@NotNull
	private int loadingAddId;
	@NotNull
	private int unloadingAddId;
	
	public OrderDto() {
		super();
	}
	public OrderDto(int id, String orderdate, int cargoId, String cargoName, String cargoDescription, int cargoWt,
			int cargoCount, int loadingAddId, int unloadingAddId,String mail) {
		super();
		this.id = id;
		this.orderdate = orderdate;
		this.cargoId = cargoId;
		this.cargoName = cargoName;
		this.cargoDescription = cargoDescription;
		this.cargoWt = cargoWt;
		this.cargoCount = cargoCount;
		this.loadingAddId = loadingAddId;
		this.unloadingAddId = unloadingAddId;
		this.mail=mail;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	public int getCargoId() {
		return cargoId;
	}
	public void setCargoId(int cargoId) {
		this.cargoId = cargoId;
	}
	public String getCargoName() {
		return cargoName;
	}
	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}
	public String getCargoDescription() {
		return cargoDescription;
	}
	public void setCargoDescription(String cargoDescription) {
		this.cargoDescription = cargoDescription;
	}
	public int getCargoWt() {
		return cargoWt;
	}
	public void setCargoWt(int cargoWt) {
		this.cargoWt = cargoWt;
	}
	public int getCargoCount() {
		return cargoCount;
	}
	public void setCargoCount(int cargoCount) {
		this.cargoCount = cargoCount;
	}
	public int getLoadingAddId() {
		return loadingAddId;
	}
	public void setLoadingAddId(int loadingAddId) {
		this.loadingAddId = loadingAddId;
	}
	public int getUnloadingAddId() {
		return unloadingAddId;
	}
	public void setUnloadingAddId(int unloadingAddId) {
		this.unloadingAddId = unloadingAddId;
	}
}