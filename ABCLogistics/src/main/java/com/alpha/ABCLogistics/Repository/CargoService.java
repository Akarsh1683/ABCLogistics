package com.alpha.ABCLogistics.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.ABCLogistics.Entity.Cargo;

public interface CargoService extends JpaRepository<Cargo, Integer>{

}