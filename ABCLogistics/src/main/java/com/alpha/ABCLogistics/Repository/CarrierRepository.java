package com.alpha.ABCLogistics.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.ABCLogistics.Entity.Carrier;

@Repository
public interface CarrierRepository extends JpaRepository<Carrier, Integer> {

}