package com.alpha.ABCLogistics.Exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alpha.ABCLogistics.DTO.ResponseStructure;


@RestControllerAdvice
public class GlobalExceptionHandler {
	//Handle Order Exceptions
	@ExceptionHandler(AddressNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleAddressNotFoundException() {
		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Address Not Found");
		rs.setData("Address Not Found");
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(AddressAlreadyPresentException.class)
	public ResponseEntity<ResponseStructure<String>> handleAddressAlreadyPresentException() {
		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setStatuscode(HttpStatus.NOT_ACCEPTABLE.value());
		rs.setMessage("Address Already Exists");
		rs.setData("Address Already Exists");
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_ACCEPTABLE);
	}
	//Handle Carrier Exceptions
	@ExceptionHandler(CarrierNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleCarrierNotFoundException() {
		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Carrier Not Found");
		rs.setData("Carrier Not Found");
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(CarrierAlreadyPresentException.class)
	public ResponseEntity<ResponseStructure<String>> handleCarrierAlreadyPresentException() {
		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setStatuscode(HttpStatus.NOT_ACCEPTABLE.value());
		rs.setMessage("Carrier Already Exists");
		rs.setData("Carrier Already Exists");
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_ACCEPTABLE);
	}
	//Handle Truck Exceptions
	@ExceptionHandler(TruckNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleTruckNotFoundException() {
		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Truck Not Found");
		rs.setData("Truck Not Found");
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(TruckAlreadyPresentException.class)
	public ResponseEntity<ResponseStructure<String>> handleTruckAlreadyPresentException() {
		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setStatuscode(HttpStatus.NOT_ACCEPTABLE.value());
		rs.setMessage("Truck Already Exists");
		rs.setData("Truck Already Exists");
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_ACCEPTABLE);
	}
	//Handle Driver Exceptions
	@ExceptionHandler(DriverNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleDriverNotFoundException() {
		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Driver Not Found");
		rs.setData("Driver Not Found");
		return new ResponseEntity<>(rs, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DriverAlreadyPresentException.class)
	public ResponseEntity<ResponseStructure<String>> handleDriverAlreadyPresentException() {
		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setStatuscode(HttpStatus.NOT_ACCEPTABLE.value());
		rs.setMessage("Driver Already Exists");
		rs.setData("Driver Already Exists");
		return new ResponseEntity<>(rs, HttpStatus.NOT_ACCEPTABLE);
	}
	//Handle Order Exceptions
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleOrderNotFoundException(OrderNotFoundException ex) {
		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setMessage(ex.getMessage());
		rs.setData("Order Not Found");
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(OrderAlreadyExistsException.class)
	public ResponseEntity<ResponseStructure<String>> handleOrderAlreadyExistsException(OrderAlreadyExistsException ex) {
		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setStatuscode(HttpStatus.NOT_ACCEPTABLE.value());
		rs.setMessage(ex.getMessage());
		rs.setData("Order Already Exists");
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_ACCEPTABLE);
	}
	//Handle Validation Exceptions
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseStructure<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		List<FieldError> ferror = ex.getFieldErrors();
		Map<String,String> validationmap = new HashMap<String, String>();
		for(FieldError fieldError : ferror) {
			validationmap.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		ResponseStructure<Map<String,String>> responseStructure = new ResponseStructure<Map<String,String>>();
		responseStructure.setStatuscode(HttpStatus.NOT_ACCEPTABLE.value());
		responseStructure.setMessage("Validation Problem");
		responseStructure.setData(validationmap);
		return new ResponseEntity<ResponseStructure<Map<String,String>>>(responseStructure,HttpStatus.NOT_ACCEPTABLE);
	}
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ResponseStructure<String>> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		responseStructure.setMessage("Missing Parameters");
		responseStructure.setData(ex.getMessage());
		responseStructure.setStatuscode(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.BAD_REQUEST);
	}
	
}