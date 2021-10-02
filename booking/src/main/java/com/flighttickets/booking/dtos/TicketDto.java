package com.flighttickets.booking.dtos;

import com.flighttickets.booking.data.entities.Passenger;
import com.flighttickets.booking.data.entities.Tickets;
import lombok.Data;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
public class TicketDto {
    private int id;
    private String name;
    private String email;
    private int user_id;
    private LocalDate ticket_book_date;
    private String status;
    private String ticket_type;
    private String seat_numbers;
    private int number_of_seats;
    private Long schedule_id;
    private Long flight_id;
    private int pnrNumber;
    private int price;
    private int discount_amount;
    private int final_price;
    private String start_from;
    private String end_to;
    private Set<PassengerDto> passenger;

}
