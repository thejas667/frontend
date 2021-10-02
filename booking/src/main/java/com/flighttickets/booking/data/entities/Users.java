package com.flighttickets.booking.data.entities;

import lombok.Data;
import sun.security.util.Password;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(unique = true)
    private String email;
    private String phone;
    private String password;
    private String role;
}
