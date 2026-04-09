package com.alpha.ABCLogistics.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.ABCLogistics.Entity.Truck;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Integer>{

}