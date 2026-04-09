package com.alpha.ABCLogistics.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.ABCLogistics.DTO.ResponseStructure;
import com.alpha.ABCLogistics.Entity.Carrier;
import com.alpha.ABCLogistics.Exception.CarrierAlreadyPresentException;
import com.alpha.ABCLogistics.Exception.CarrierNotFoundException;
import com.alpha.ABCLogistics.Repository.CarrierRepository;

@Service
public class CarrierService {
	@Autowired
	private CarrierRepository carrierRepository;
	public ResponseEntity<ResponseStructure<Carrier>> saveCarrier(Carrier carrier) {
		Optional<Carrier> carrierOpt =carrierRepository.findById(carrier.getId());
		if(carrierOpt.isPresent()) {
			throw new CarrierAlreadyPresentException();
		}
		Carrier savedCarrier = carrierRepository.save(carrier);
		ResponseStructure<Carrier> responseStructure = new ResponseStructure<Carrier>();
		responseStructure.setData(savedCarrier);
		responseStructure.setMessage("Address Saved");
		responseStructure.setStatuscode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Carrier>>(responseStructure, HttpStatus.ACCEPTED);
	}
	public ResponseEntity<ResponseStructure<Carrier>> findCarrier(int id) {
		Optional<Carrier> carrierOpt =carrierRepository.findById(id);
		if(carrierOpt.isEmpty()) {
			throw new CarrierNotFoundException();
		}
		Carrier carrier = carrierOpt.get();
		ResponseStructure<Carrier> responseStructure = new ResponseStructure<Carrier>();
		responseStructure.setData(carrier);
		responseStructure.setMessage("Address Found");
		responseStructure.setStatuscode(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<Carrier>>(responseStructure, HttpStatus.FOUND);
	}
	public ResponseEntity<ResponseStructure<Carrier>> deleteCarrier(int id) {
		Optional<Carrier> carrierOpt =carrierRepository.findById(id);
		if(carrierOpt.isEmpty()) {
			throw new CarrierNotFoundException();
		}
		Carrier carrier = carrierOpt.get();
		carrierRepository.delete(carrier);
		ResponseStructure<Carrier> responseStructure = new ResponseStructure<Carrier>();
		responseStructure.setData(carrier);
		responseStructure.setMessage("Address Deleted");
		responseStructure.setStatuscode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Carrier>>(responseStructure, HttpStatus.ACCEPTED);
		
	}

}