package com.CarPerHour.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
import java.io.IOException;
import java.util.List;
 
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.CarPerHour.dto.BookACarDto;
import com.CarPerHour.dto.CarDto;
import com.CarPerHour.dto.CarDtoListDto;
import com.CarPerHour.dto.SearchCarDto;
import com.CarPerHour.services.admin.AdminService;
 

@SpringBootTest
public class AdminControllerTest {
 
    @Mock
    private AdminService adminService;
 
    @InjectMocks
    private AdminController adminController;
 
    @Test
    public void testPostCar() throws IOException {
        // Arrange
        CarDto carDto = new CarDto();
        MultipartFile image = new MockMultipartFile("image", "Hello, World!".getBytes());
        carDto.setImage(image);
        when(adminService.postCar(any(CarDto.class))).thenReturn(true);
 
        // Act
        ResponseEntity<?> result = adminController.postCar(carDto);
 
        // Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
       
    }
 
    @Test
    public void testGetAllCars() {
       
        when(adminService.getAllCars()).thenReturn(List.of(new CarDto()));
 
        ResponseEntity<?> result = adminController.getAllCars();
 
       
        assertEquals(HttpStatus.OK, result.getStatusCode());
       
    }
 
    @Test
    public void testDeleteCar() {
       
        Long id = 1L;
        doNothing().when(adminService).deleteCar(id);
 
       
        ResponseEntity<Void> result = adminController.deleteCar(id);
 
       
        assertEquals(HttpStatus.OK, result.getStatusCode());
       
    }
 
    @Test
    public void testGetCarById() {
       
        Long id = 1L;
        when(adminService.getCarById(id)).thenReturn(new CarDto());
 
       
        ResponseEntity<CarDto> result = adminController.getCarById(id);
 
       
        assertEquals(HttpStatus.OK, result.getStatusCode());
       
    }
 
    @Test
    public void testUpdateCarById() throws IOException {
        // Arrange
        Long id = 1L;
        CarDto carDto = new CarDto();
        when(adminService.updateCar(id, carDto)).thenReturn(true);
 
        // Act
        ResponseEntity<Void> result = adminController.updateCarById(id, carDto);
 
        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
       
    }
 
    @Test
    public void testGetBookings() {
        // Arrange
        when(adminService.getBookings()).thenReturn(List.of(new BookACarDto()));
 
        // Act
        ResponseEntity<List<BookACarDto>> result = adminController.getBookings();
 
        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
       
    }
 
    @Test
    public void testChangeBookingStatus() {
        // Arrange
        Long bookingId = 1L;
        String status = "Approve";
        when(adminService.changeBookingStatus(bookingId, status)).thenReturn(true);
 
        // Act
        ResponseEntity<?> result = adminController.changeBookingStatus(bookingId, status);
 
        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
//        verify(adminService, times(1)).changeBookingStatus(bookingId, status);
    }
 
    @Test
    public void testSearchCar() {
        // Arrange
        SearchCarDto searchCarDto = new SearchCarDto();
        when(adminService.searchCar(searchCarDto)).thenReturn(new CarDtoListDto());
 
        // Act
        ResponseEntity<?> result = adminController.searchCar(searchCarDto);
 
        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
       
    }
}
