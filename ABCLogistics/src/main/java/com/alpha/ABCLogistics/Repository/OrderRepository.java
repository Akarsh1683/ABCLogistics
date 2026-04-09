package com.alpha.ABCLogistics.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.ABCLogistics.Entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	
}