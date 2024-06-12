package com.CarPerHour.services.customer;

import java.util.List;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.CarPerHour.dto.BookACarDto;
import com.CarPerHour.dto.CarDto;
import com.CarPerHour.dto.CarDtoListDto;
import com.CarPerHour.dto.SearchCarDto;
import com.CarPerHour.entity.BookACar;
import com.CarPerHour.entity.Car;
import com.CarPerHour.entity.User;
import com.CarPerHour.enums.BookCarStatus;
import com.CarPerHour.repository.BookACarRepository;
import com.CarPerHour.repository.CarRepository;
import com.CarPerHour.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	private final CarRepository carRepository;
	private final UserRepository userRepository;
	private final BookACarRepository bookACarRepository;

	@Override
	public List<CarDto> getAllCars() {

		return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
	}

	@Override
	public boolean bookACar(BookACarDto bookACarDto) {
		Optional<Car> optionalCar = carRepository.findById(bookACarDto.getCarId());
		Optional<User> optionalUser = userRepository.findById(bookACarDto.getUserId());
		if (optionalCar.isPresent() && optionalUser.isPresent()) {
			Car existingCar = optionalCar.get();
			BookACar bookACar = new BookACar();
			bookACar.setUser(optionalUser.get());
			bookACar.setCar(existingCar);
			bookACar.setBookCarStatus(BookCarStatus.PENDING);
			long diffInMilliSeconds = bookACarDto.getToDate().getTime() - bookACarDto.getFromDate().getTime();
			long days = TimeUnit.MILLISECONDS.toDays(diffInMilliSeconds); // Use MILLISECONDS instead of MICROSECONDS
			bookACar.setDays(days);
			bookACar.setFromDate(bookACarDto.getFromDate()); // Set fromDate from bookACarDto
			bookACar.setToDate(bookACarDto.getToDate()); // Set toDate from bookACarDto
			bookACar.setPrice(existingCar.getPrice() * days);
			bookACarRepository.save(bookACar);
			return true;
		}

		return false;
	}

	@Override
	public CarDto getCarById(Long carId) {
		Optional<Car> optionalCar = carRepository.findById(carId);
		return optionalCar.map(Car::getCarDto).orElse(null);
	}

	@Override
	public List<BookACarDto> getBookingsByUserId(Long userId) {
		return bookACarRepository.findAllByUserId(userId).stream().map(BookACar::getBookACarDto)
				.collect(Collectors.toList());
	}

	@Override
	public CarDtoListDto searchCar(SearchCarDto searchCarDto) {
		Car car = new Car();
		car.setBrand(searchCarDto.getBrand());
		car.setType(searchCarDto.getType());
		car.setTransmission(searchCarDto.getTransmission());
		car.setColor(searchCarDto.getColor());
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
				.withMatcher("brand", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("transmission", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("color", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		Example<Car> carExample = Example.of(car, exampleMatcher);
		List<Car> carList = carRepository.findAll(carExample);
		CarDtoListDto carDtoListDto = new CarDtoListDto();
		carDtoListDto.setCarDtoList(carList.stream().map(Car::getCarDto).collect(Collectors.toList()));
		return carDtoListDto;
	}	

}
