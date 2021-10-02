package com.flighttickets.booking.dtos;

import lombok.Data;
import sun.security.util.Password;

@Data
public class UserDto {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String role;
}
