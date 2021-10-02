package com.flighttickets.booking.data.repos;

import com.flighttickets.booking.data.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
}
