package com.alpha.ABCLogistics.Exception;

public class OrderNotFoundException extends RuntimeException {
	public OrderNotFoundException(String message) {
		super(message);
	}
	
}