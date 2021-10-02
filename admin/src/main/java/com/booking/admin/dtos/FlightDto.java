package com.booking.admin.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class FlightDto {
    private int id;
    private Long flightNo;
    private String name;
    private String logo;
    private int capacity;
    private int businessClassSeats;
    private int generalSeats;
    private int businessClassSeatPrice;
    private int generalSeatPrice;
    private int discount;
    private Set<SchedulesDto> schedules;
}
