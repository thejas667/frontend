package com.booking.admin.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter@Getter
@AllArgsConstructor@NoArgsConstructor
public class ResultSet {

    @Id
    private Long id;
    private Long fid;
    private Long business_class_seat_price;
    private Long business_class_seats;
    private Integer capacity;
    private Integer flight_no;
    private Integer general_seat_price;
    private Integer general_seats;
    private String name;
    private String logo;
    private String available_days;
    private String start_time;
    private String end_time;
    private String end_to;
    private String start_from;
}
