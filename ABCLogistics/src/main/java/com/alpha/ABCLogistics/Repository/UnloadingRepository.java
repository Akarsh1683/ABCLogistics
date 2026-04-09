package com.alpha.ABCLogistics.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.ABCLogistics.Entity.Unloading;

@Repository
public interface UnloadingRepository extends JpaRepository<Unloading, Integer> {

}