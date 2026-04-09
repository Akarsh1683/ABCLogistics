package com.alpha.ABCLogistics.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.alpha.ABCLogistics.DTO.LoadingDto;
import com.alpha.ABCLogistics.DTO.OrderDto;
import com.alpha.ABCLogistics.DTO.ResponseStructure;
import com.alpha.ABCLogistics.DTO.UnloadingDto;
import com.alpha.ABCLogistics.Entity.Address;
import com.alpha.ABCLogistics.Entity.Cargo;
import com.alpha.ABCLogistics.Entity.Driver;
import com.alpha.ABCLogistics.Entity.Loading;
import com.alpha.ABCLogistics.Entity.Order;
import com.alpha.ABCLogistics.Entity.Truck;
import com.alpha.ABCLogistics.Entity.Unloading;
import com.alpha.ABCLogistics.Exception.AddressNotFoundException;
import com.alpha.ABCLogistics.Exception.CargoAlreadyExistsException;
import com.alpha.ABCLogistics.Exception.OrderAlreadyExistsException;
import com.alpha.ABCLogistics.Exception.OrderCannotBeCancelledException;
import com.alpha.ABCLogistics.Exception.OrderNotFoundException;
import com.alpha.ABCLogistics.Exception.TruckCapacityExceededException;
import com.alpha.ABCLogistics.Exception.TruckNotFoundException;
import com.alpha.ABCLogistics.Repository.AddressRepository;
import com.alpha.ABCLogistics.Repository.CargoService;
import com.alpha.ABCLogistics.Repository.OrderRepository;
import com.alpha.ABCLogistics.Repository.TruckRepository;

@Service
public class OrderService {
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	CargoService cargoService;
	@Autowired
	TruckRepository truckRepository;
	@Autowired
	MailService mailservice;
	public ResponseEntity<ResponseStructure<Order>> saveOrder(OrderDto dto) {
		Order odd = new Order();
		if(orderRepository.existsById(dto.getId())) {
			throw new OrderAlreadyExistsException("Order with id " + dto.getId() + " already exists");
		}			
		odd.setId(dto.getId());
		odd.setOrderdate(dto.getOrderdate());
		odd.setMail(dto.getMail());
		int cost = 10*(dto.getCargoWt()*dto.getCargoCount());
		odd.setCost(cost);
		if(cargoService.existsById(dto.getCargoId())) {
			throw new CargoAlreadyExistsException("Cargo with id " + dto.getCargoId() + " already exists");
		}
		Cargo cargo = new Cargo(dto.getCargoId(), dto.getCargoName(), dto.getCargoDescription(), dto.getCargoWt(), dto.getCargoCount());
		odd.setCargo(cargo);
		Loading load = new Loading();
		Address loadAdd = addressRepository.findById(dto.getLoadingAddId()).orElseThrow(()->new AddressNotFoundException("Address with id " + dto.getLoadingAddId() + " not found"));
		load.setAddress(loadAdd);
		odd.setLoading(load);
		Unloading unload = new Unloading();
		Address unloadAdd = addressRepository.findById(dto.getUnloadingAddId()).orElseThrow(()->new AddressNotFoundException("Address with id " + dto.getUnloadingAddId() + " not found"));
		unload.setAddress(unloadAdd);
		odd.setUnloading(unload);
		Order oredersaved=orderRepository.save(odd);
		ResponseStructure<Order> responseStructure = new ResponseStructure<Order>();
		responseStructure.setData(odd);
		responseStructure.setMessage("Order saved successfully");
		responseStructure.setStatuscode(HttpStatus.CREATED.value());
		String subject="order placed Succesfully, Thank u for ordering";
		String content="your order placed from"+oredersaved.getLoading().getAddress().getCity()+"to"+oredersaved.getUnloading().getAddress().getCity()+"for the cost of rupees"+oredersaved.getCost();
		mailservice.sendMail(oredersaved.getMail(), subject,content);
		return new ResponseEntity<ResponseStructure<Order>>(responseStructure, HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<Order>> findOrder(int id) {
		Order order = orderRepository.findById(id).orElseThrow(()->new OrderNotFoundException("Order with id " + id + " not found"));
		ResponseStructure<Order> responseStructure = new ResponseStructure<Order>();
		responseStructure.setData(order);
		responseStructure.setMessage("Order found successfully");
		responseStructure.setStatuscode(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<Order>>(responseStructure, HttpStatus.FOUND);
	}
	public ResponseEntity<ResponseStructure<Order>> deleteOrder(int id) {
		Order order = orderRepository.findById(id).orElseThrow(()->new OrderNotFoundException("Order with id " + id + " not found"));
		orderRepository.delete(order);
		ResponseStructure<Order> responseStructure = new ResponseStructure<Order>();
		responseStructure.setData(order);
		responseStructure.setMessage("Order deleted successfully");
		responseStructure.setStatuscode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Order>>(responseStructure, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Order>> updateOrder(int orderid, int truckid) {
		Order ord = orderRepository.findById(orderid).orElseThrow(()->new OrderNotFoundException("Order with id " + orderid + " not found"));
		Truck truck = truckRepository.findById(truckid).orElseThrow(()->new TruckNotFoundException("Truck with id " + truckid + " not found"));
		int totalwtoforder = (ord.getCargo().getWeight()*ord.getCargo().getCount());
		int truckcapacity = truck.getCapacity();
		if(truckcapacity>=totalwtoforder) {
			ord.setCarrier(truck.getCarrier());
			truck.setCapacity(truck.getCapacity()-totalwtoforder);
			truckRepository.save(truck);
			orderRepository.save(ord);
		}else {
			throw new TruckCapacityExceededException("Order weight "+totalwtoforder+" exceeds the available capacity of truck "+truckcapacity);
		}
		ResponseStructure<Order> responseStructure = new ResponseStructure<Order>();
		responseStructure.setData(ord);
		responseStructure.setMessage("Order updated successfully");
		responseStructure.setStatuscode(HttpStatus.ACCEPTED.value());
		String sub = "Carrier Assigned For Order";
		String content = "Dear Customer,\n\n"
			    + "Your order has been successfully assigned to the following carrier:\n\n"
			    + "Carrier Name: " + ord.getCarrier().getName() + "\n"
			    + "Contact Number: " + ord.getCarrier().getContact() + "\n"
			    + "Email: " + ord.getCarrier().getMail() + "\n\n"
			    + "You will be contacted soon for further updates.\n\n"
			    + "Thank you for choosing our service.";
		mailservice.sendMail(ord.getMail(), sub, content);
		return new ResponseEntity<ResponseStructure<Order>>(responseStructure, HttpStatus.ACCEPTED);
	}
	public ResponseEntity<ResponseStructure<Order>> updateLoading(int orderid, LoadingDto ldto) {
		Order ord = orderRepository.findById(orderid).orElseThrow(()->new OrderNotFoundException("Order with id " + orderid + " not found"));
		ord.getLoading().setDate(ldto.getDate());
		ord.getLoading().setTime(ldto.getTime());
		ord.setStatus("pending");
		Order saved = orderRepository.save(ord);
		ResponseStructure<Order> responseStructure = new ResponseStructure<Order>();
		responseStructure.setData(saved);
		responseStructure.setMessage("Order updated successfully");
		responseStructure.setStatuscode(HttpStatus.ACCEPTED.value());
		String subject = "Order Loaded into the Truck";

		String content = "Dear Customer,\n\n"
		    + "We’re pleased to inform you that your order (Order ID: " + o.getId() + ") "
		    + "has been successfully loaded into the truck and is now ready for dispatch.\n\n"
		    + "Loading Details:\n"
		    + "------------------------------------\n"
		    + "Loading City: " + ord.getLoading().getAddress().getCity() + "\n"
		    + "Date: " + ord.getLoading().getDate() + "\n"
		    + "Time: " + ord.getLoading().getTime() + "\n"
		    + "------------------------------------\n\n"
		    + "Your order is currently in the 'Transit' stage and will be on its way shortly.\n\n"
		    + "Thank you for choosing ABC Logistics.\n\n"
		    + "Best regards,\n"
		    + "ABC Logistics Team";
		
		mailservice.sendMail(ord.getMail(), subject, content);
		return new ResponseEntity<ResponseStructure<Order>>(responseStructure, HttpStatus.ACCEPTED);

	}
	public ResponseEntity<ResponseStructure<Order>> unUpdateLoading(int orderid, UnloadingDto udto) {
		Order ord = orderRepository.findById(orderid).orElseThrow(()->new OrderNotFoundException("Order with id " + orderid + " not found"));
		ord.getUnloading().setDate(udto.getDate());
		ord.getUnloading().setTime(udto.getTime());
		ord.setStatus("pending");
		Order saved = orderRepository.save(ord);
		ResponseStructure<Order> responseStructure = new ResponseStructure<Order>();
		responseStructure.setData(saved);
		responseStructure.setMessage("Order updated successfully");
		responseStructure.setStatuscode(HttpStatus.ACCEPTED.value());
		String subject ="Order Delivered Successfully";
		String content = "Dear Customer,\n\n"
			    + "We’re happy to inform you that your order (Order ID: " + saved.getId() + ") "
			    + "has been successfully delivered to its destination.\n\n"
			    + "Delivery Details:\n"
			    + "------------------------------------\n"
			    + "Destination City: " + saved.getUnloading().getAddress().getCity() + "\n"
			    + "Date: " + saved.getUnloading().getDate() + "\n"
			    + "Time: " + saved.getUnloading().getTime() + "\n"
			    + "------------------------------------\n\n"
			    + "Thank you for choosing ABC Logistics. We hope to serve you again soon.\n\n"
			    + "Best regards,\n"
			    + "ABC Logistics Team";
		mailservice.sendMail(saved.getMail(), subject, content);
		return new ResponseEntity<ResponseStructure<Order>>(responseStructure, HttpStatus.ACCEPTED);
		
	}
	public ResponseEntity<ResponseStructure<Order>> cancelOrder(int orderId) {
		Order order = findOrder(orderId).getBody().getData();
		Order saved = order;
		if(order.getStatus().equals("placed")) {
			order.setStatus("cancelled");
			saved = orderRepository.save(order);
		}else {
			throw new OrderCannotBeCancelledException("Order Cannot be cancelled because order status is "+order.getStatus());
		}
		ResponseStructure<Order> responseStructure = new ResponseStructure<Order>();
		responseStructure.setData(saved);
		responseStructure.setMessage("Order cancelled successfully");
		responseStructure.setStatuscode(HttpStatus.ACCEPTED.value());
		String subject ="Order Cancelled!";
		String content = "Dear Customer,\n\n"
			    + "We regret to inform you that your order (Order ID: " + saved.getId() + ") "
			    + "has been successfully cancelled as per your request.\n\n"
			    + "If you have any questions or need further assistance, please feel free to contact our customer support team.\n\n"
			    + "Thank you for considering ABC Logistics. We hope to serve you again in the future.\n\n"
			    + "Best regards,\n"
			    + "ABC Logistics Team";
		mailservice.sendMail(saved.getMail(), subject, content);
		return new ResponseEntity<ResponseStructure<Order>>(responseStructure, HttpStatus.ACCEPTED);
	}

}