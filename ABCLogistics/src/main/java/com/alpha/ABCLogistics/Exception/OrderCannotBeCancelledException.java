package com.alpha.ABCLogistics.Exception;

public class OrderCannotBeCancelledException extends RuntimeException {

	public OrderCannotBeCancelledException() {
		super();
	}

	public OrderCannotBeCancelledException(String message) {
		super(message);
	}

}