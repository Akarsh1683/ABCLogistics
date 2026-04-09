package com.alpha.ABCLogistics.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.ABCLogistics.Entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
