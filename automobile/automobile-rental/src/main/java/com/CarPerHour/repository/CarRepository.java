package com.CarPerHour.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CarPerHour.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car,Long>{

	
}
