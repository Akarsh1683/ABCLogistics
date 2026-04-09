package com.alpha.ABCLogistics.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.ABCLogistics.DTO.OrderDto;
import com.alpha.ABCLogistics.DTO.ResponseStructure;
import com.alpha.ABCLogistics.Entity.Order;
import com.alpha.ABCLogistics.Service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	OrderService orderService;
	@PostMapping("/saveorder")
	public ResponseEntity<ResponseStructure<Order>> saveOrder(@RequestBody @Valid OrderDto orderDto) {
		return orderService.saveOrder(orderDto);
	}
	@PostMapping("/cancelorder")
	public ResponseEntity<ResponseStructure<Order>> cancelOrder(@RequestParam int id) {
		return orderService.cancelOrder(id);
	}
}