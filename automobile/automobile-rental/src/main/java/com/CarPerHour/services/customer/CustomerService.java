package com.CarPerHour.services.customer;

import java.util.List;

import org.springframework.stereotype.Service;

import com.CarPerHour.dto.BookACarDto;
import com.CarPerHour.dto.CarDto;
import com.CarPerHour.dto.CarDtoListDto;
import com.CarPerHour.dto.SearchCarDto;

@Service
public interface CustomerService {
	
	List<CarDto> getAllCars();
	boolean bookACar(BookACarDto bookACarDto);
	CarDto getCarById(Long carId);
	List<BookACarDto> getBookingsByUserId(Long userId);
	CarDtoListDto searchCar(SearchCarDto searchCarDto);

}
