package com.CarPerHour.services.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.CarPerHour.dto.BookACarDto;
import com.CarPerHour.dto.CarDto;
import com.CarPerHour.dto.CarDtoListDto;
import com.CarPerHour.dto.SearchCarDto;

@Service
public interface AdminService {
	
	boolean postCar(CarDto carDto)throws IOException;
	
	List<CarDto> getAllCars();
	
	void deleteCar(Long id);
	
	CarDto getCarById(Long id);
	
	boolean updateCar(Long carId, CarDto carDto)throws IOException ;
	
	List<BookACarDto> getBookings();
	
	boolean changeBookingStatus(Long bookingId,String status);
	
	CarDtoListDto searchCar(SearchCarDto searchCarDto);
}
