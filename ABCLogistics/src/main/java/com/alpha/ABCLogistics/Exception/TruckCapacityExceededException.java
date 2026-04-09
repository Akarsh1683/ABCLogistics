package com.alpha.ABCLogistics.Exception;

public class TruckCapacityExceededException extends RuntimeException {

	public TruckCapacityExceededException(String Message) {
		super(Message);
	}
	public TruckCapacityExceededException() {
		super();
	}

}
