package com.alpha.ABCLogistics.Exception;

public class OrderAlreadyExistsException extends RuntimeException {
	public OrderAlreadyExistsException(String message) {
		super(message);
	}
}