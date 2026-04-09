
package com.alpha.ABCLogistics.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.ABCLogistics.DTO.ResponseStructure;
import com.alpha.ABCLogistics.Entity.Address;
import com.alpha.ABCLogistics.Exception.AddressAlreadyPresentException;
import com.alpha.ABCLogistics.Exception.AddressNotFoundException;
import com.alpha.ABCLogistics.Repository.AddressRepository;
@Service
public class AddressService {
	@Autowired
	AddressRepository addressRepository;
	
	public ResponseEntity<ResponseStructure<Address>> saveAddress(Address address) {
		Optional<Address> addOptional = addressRepository.findById(address.getId());
		if(addOptional.isPresent()) {
			throw new AddressAlreadyPresentException();
		}
		Address savedAddress = addressRepository.save(address);
		ResponseStructure<Address> responseStructure = new ResponseStructure<Address>();
		responseStructure.setData(savedAddress);
		responseStructure.setMessage("Address Saved");
		responseStructure.setStatuscode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Address>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Address>> findAddress(int id) {
		Optional<Address> addOptional = addressRepository.findById(id);
		if(!addOptional.isPresent()) {
			throw new AddressNotFoundException();
		}
		ResponseStructure<Address> responseStructure = new ResponseStructure<Address>();
		Address address = addOptional.get();
		responseStructure.setData(address);
		responseStructure.setMessage("Address Found");
		responseStructure.setStatuscode(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<Address>>(responseStructure, HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<Address>> deleteAddress(int id) {
		Optional<Address> addOptional = addressRepository.findById(id);
		if(!addOptional.isPresent()) {
			throw new AddressNotFoundException();
		}
		Address address = addOptional.get();
		addressRepository.delete(address);
		ResponseStructure<Address> responseStructure = new ResponseStructure<Address>();
		responseStructure.setData(address);
		responseStructure.setMessage("Address Deleted");
		responseStructure.setStatuscode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Address>>(responseStructure, HttpStatus.ACCEPTED);
	}
}
