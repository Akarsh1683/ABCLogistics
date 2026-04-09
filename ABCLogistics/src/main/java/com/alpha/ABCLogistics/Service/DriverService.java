package com.alpha.ABCLogistics.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.ABCLogistics.DTO.ResponseStructure;
import com.alpha.ABCLogistics.Entity.Carrier;
import com.alpha.ABCLogistics.Entity.Driver;
import com.alpha.ABCLogistics.Entity.Truck;
import com.alpha.ABCLogistics.Exception.CarrierNotFoundException;
import com.alpha.ABCLogistics.Exception.DriverAlreadyPresentException;
import com.alpha.ABCLogistics.Exception.DriverNotFoundException;
import com.alpha.ABCLogistics.Exception.TruckNotFoundException;
import com.alpha.ABCLogistics.Repository.CarrierRepository;
import com.alpha.ABCLogistics.Repository.DriverRepository;
import com.alpha.ABCLogistics.Repository.TruckRepository;

@Service
public class DriverService {

	@Autowired
	private DriverRepository driverRepository;
	@Autowired
	private TruckRepository truckRepository;
	@Autowired
	private CarrierRepository carrierRepository;

	public ResponseEntity<ResponseStructure<Driver>> saveDriver(Driver driver) {
		Optional<Driver> driverOpt = driverRepository.findById(driver.getId());
		if (driverOpt.isPresent()) {
			throw new DriverAlreadyPresentException();
		}
		Driver savedDriver = driverRepository.save(driver);

		ResponseStructure<Driver> responseStructure = new ResponseStructure<>();
		responseStructure.setData(savedDriver);
		responseStructure.setMessage("Driver Saved");
		responseStructure.setStatuscode(HttpStatus.CREATED.value());

		return new ResponseEntity<ResponseStructure<Driver>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Driver>> findDriver(int id) {
		Optional<Driver> driverOpt = driverRepository.findById(id);
		if (driverOpt.isEmpty()) {
			throw new DriverNotFoundException();
		}
		Driver driver = driverOpt.get();

		ResponseStructure<Driver> responseStructure = new ResponseStructure<>();
		responseStructure.setData(driver);
		responseStructure.setMessage("Driver Found");
		responseStructure.setStatuscode(HttpStatus.FOUND.value());

		return new ResponseEntity<ResponseStructure<Driver>>(responseStructure, HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<Driver>> deleteDriver(int id) {
		Optional<Driver> driverOpt = driverRepository.findById(id);
		if (driverOpt.isEmpty()) {
			throw new DriverNotFoundException();
		}
		Driver driver = driverOpt.get();
		driverRepository.delete(driver);

		ResponseStructure<Driver> responseStructure = new ResponseStructure<>();
		responseStructure.setData(driver);
		responseStructure.setMessage("Driver Deleted");
		responseStructure.setStatuscode(HttpStatus.ACCEPTED.value());

		return new ResponseEntity<ResponseStructure<Driver>>(responseStructure, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<ResponseStructure<Driver>> updateDriver(int driverid, int carrierid, int truckid) {
		Truck t = truckRepository.findById(truckid).orElseThrow(() -> new TruckNotFoundException("Truck with id"+truckid+"not found"));
		Driver d = driverRepository.findById(driverid).orElseThrow(() -> new DriverNotFoundException());
		Carrier c = carrierRepository.findById(carrierid).orElseThrow(() -> new CarrierNotFoundException());
		d.setTruck(t);
		d.setCarrier(c);
		t.setCarrier(c);
		Driver savedDriver = driverRepository.save(d);

		ResponseStructure<Driver> responseStructure = new ResponseStructure<>();
		responseStructure.setData(savedDriver);
		responseStructure.setMessage("Driver Updated");
		responseStructure.setStatuscode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Driver>>(responseStructure, HttpStatus.ACCEPTED);
	}
}