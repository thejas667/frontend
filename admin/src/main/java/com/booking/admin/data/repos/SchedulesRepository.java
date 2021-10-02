package com.booking.admin.data.repos;

import com.booking.admin.data.entities.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulesRepository extends JpaRepository<Schedules, Integer> {
//    List<Schedules> findByFlight(int id);

}
