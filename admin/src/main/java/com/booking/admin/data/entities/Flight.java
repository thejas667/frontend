package com.booking.admin.data.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

//@Data
@Setter
@Getter
@Entity
@Table(name="flight_details")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long flightNo;
    @Column(unique = true)
    private String name;
    private String logo;
    private int capacity;
    private int businessClassSeats;
    private int generalSeats;
    private int businessClassSeatPrice;
    private int generalSeatPrice;
    private int discount;
    @OneToMany(targetEntity = Schedules.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="flight_id",referencedColumnName = "id")
    private Set<Schedules> schedules;

}
