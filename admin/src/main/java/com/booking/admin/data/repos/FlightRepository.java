package com.booking.admin.data.repos;

import com.booking.admin.data.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    Flight findByFlightNo(Long flightNo);
}
