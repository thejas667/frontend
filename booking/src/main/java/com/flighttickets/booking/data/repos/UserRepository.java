package com.flighttickets.booking.data.repos;

import com.flighttickets.booking.data.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.jws.soap.SOAPBinding;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Integer> {
    Users findByEmail(String email);
    Optional<Users> findByEmailAndPassword(String email, String password);
}
