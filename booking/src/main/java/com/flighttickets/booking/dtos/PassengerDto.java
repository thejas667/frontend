package com.flighttickets.booking.dtos;

import lombok.Data;

@Data
public class PassengerDto {
    private int id;
    private String name;
    private String gender;
    private  String meal;
    private int age;
}
