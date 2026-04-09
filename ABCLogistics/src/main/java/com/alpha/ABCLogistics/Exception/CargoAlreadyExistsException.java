package com.alpha.ABCLogistics.Exception;

public class CargoAlreadyExistsException extends RuntimeException {
	public CargoAlreadyExistsException(String message) {
		super(message);
	}
}