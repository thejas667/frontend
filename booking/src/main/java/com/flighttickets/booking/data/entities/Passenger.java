package com.flighttickets.booking.data.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "passenger_details")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String gender;
    private int age;
    private String meal;

//    @ManyToOne
//    @JoinColumn(name = "ticket_id", nullable = false)
//    private Tickets tickets;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return id == passenger.id &&
                Objects.equals(name, passenger.name) &&
                Objects.equals(gender, passenger.gender) &&
                Objects.equals(age, passenger.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, age);
    }
}
