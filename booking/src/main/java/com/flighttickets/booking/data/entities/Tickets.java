package com.flighttickets.booking.data.entities;

import lombok.Data;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Table(name = "ticket")
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(targetEntity = Passenger.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="ticket_id",referencedColumnName = "id")
    private Set<Passenger> passenger;
//    @OneToMany(mappedBy = "tickets")
//    private Set<Passenger> passenger;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tickets tickets = (Tickets) o;
        return id == tickets.id &&
                Objects.equals(name, tickets.name) &&
                Objects.equals(email, tickets.email) &&
                Objects.equals(user_id, tickets.user_id) &&
                Objects.equals(ticket_book_date, tickets.ticket_book_date) &&
                Objects.equals(status, tickets.status) &&
                Objects.equals(seat_numbers, tickets.status) &&
                Objects.equals(schedule_id, tickets.schedule_id) &&
                Objects.equals(flight_id, tickets.flight_id) &&
                Objects.equals(passenger, tickets.passenger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, user_id, ticket_book_date, status, status, schedule_id, flight_id, passenger);
    }
}
