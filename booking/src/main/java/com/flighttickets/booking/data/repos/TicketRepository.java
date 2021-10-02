package com.flighttickets.booking.data.repos;

import com.flighttickets.booking.data.entities.Tickets;
import com.flighttickets.booking.dtos.TicketCount;
import com.flighttickets.booking.dtos.TicketDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Tickets, Integer> {
    Tickets findByPnrNumber(int pnrNumber);

    List<Tickets> findByEmail(String email);

    @Query(value = "SELECT count(*) as count FROM Tickets t where t.ticket_book_date>?1 and t.flight_id=?2")
    Optional<Integer> countOfFlightBookings(LocalDate current_date, Long flightNo);

    @Query(value = "SELECT count(*) as count FROM Tickets t where t.ticket_book_date>?1 and t.schedule_id=?2")
    Optional<Integer> countOfScheduleBookings(LocalDate current_date, Long schedule_id);


    @Query(value = "SELECT sum(number_of_seats) FROM Tickets t where t.ticket_book_date=?1 and t.status=?2 and t.ticket_type=?3 and t.schedule_id=?4")
    Optional<Integer> sumOfBookedTickets(LocalDate ticket_book_date, String status, String ticket_type, Long schedule_id);
}
