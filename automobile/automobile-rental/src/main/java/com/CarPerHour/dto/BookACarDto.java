package com.CarPerHour.dto;

import java.util.Date;

import com.CarPerHour.enums.BookCarStatus;

import lombok.Data;

@Data

public class BookACarDto {

    private Long id;
    private Date fromDate;
    private Date toDate;
    private Long days;
    private Long price;
    private BookCarStatus bookCarStatus;
    private Long carId;
    private Long userId;
    private String username;
    private String email;
}
