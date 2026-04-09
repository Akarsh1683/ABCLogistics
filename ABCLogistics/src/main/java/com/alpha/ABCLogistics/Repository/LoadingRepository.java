package com.alpha.ABCLogistics.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.ABCLogistics.Entity.Loading;

@Repository
public interface LoadingRepository extends JpaRepository<Loading, Integer>{
	
}