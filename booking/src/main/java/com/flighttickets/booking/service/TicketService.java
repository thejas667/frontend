package com.flighttickets.booking.service;

//import com.flighttickets.booking.data.entities.Flight;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.flighttickets.booking.configure.AdminSettings;
import com.flighttickets.booking.data.UserAuth;
import com.flighttickets.booking.data.entities.Tickets;
//import com.flighttickets.booking.data.repos.FlightRepository;
import com.flighttickets.booking.data.repos.PassengerRepository;
import com.flighttickets.booking.data.repos.TicketRepository;
import com.flighttickets.booking.dtos.ResultSet;
import com.flighttickets.booking.dtos.TicketDto;
import com.flighttickets.booking.mapper.PassengerMapper;
import com.flighttickets.booking.mapper.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TicketService {
    private TicketRepository ticketRepository;
    private RestTemplate restTemplate;
    private AdminSettings adminSettings;
    private TicketMapper ticketMapper;
    private PassengerMapper passengerMapper;
    private PassengerRepository passengerRepository;

    static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }

    @Autowired
    public TicketService(TicketRepository ticketRepository, RestTemplate restTemplate, AdminSettings adminSettings, TicketMapper ticketMapper, PassengerMapper passengerMapper, PassengerRepository passengerRepository){
        this.ticketRepository = ticketRepository;
        this.restTemplate = restTemplate;
        this.adminSettings = adminSettings;
        this.ticketMapper = ticketMapper;
        this.passengerMapper = passengerMapper;
        this.passengerRepository = passengerRepository;
    }

    public TicketDto booking(Long flight_id, TicketDto ticketDto){
        String url = adminSettings.getAdminBaseUrl() + "flight/" + flight_id;
        ResponseEntity<ResultSet> flight = restTemplate.getForEntity(url, ResultSet.class);
        System.out.println("flight data: "+ flight);
        if(Objects.nonNull(flight)){
            ticketDto.setFlight_id(flight_id);
            ticketDto.setUser_id(UserAuth.get().getUserId());
            String schedule_url = adminSettings.getAdminBaseUrl() + "flight/search/schedule/" + ticketDto.getSchedule_id();
            ResponseEntity<ResultSet> responseEntity = restTemplate.getForEntity(schedule_url, ResultSet.class);
            ResultSet result = responseEntity.getBody();
            Optional<Integer> booked_seats = ticketRepository.sumOfBookedTickets(ticketDto.getTicket_book_date(), "Confirmed", ticketDto.getTicket_type(), ticketDto.getSchedule_id());
            int seats = result.getBusiness_class_seats() - booked_seats.orElse(0);
            if(ticketDto.getNumber_of_seats() < seats){
                Random rnd = new Random();
                int PNR = 100000 + rnd.nextInt(900000);
                ticketDto.setPnrNumber(PNR);
                ticketDto.setStatus("Confirmed");
                Tickets tickets = objectMapper.convertValue(ticketDto, Tickets.class);
                Tickets ticketObject;

                ticketObject = ticketRepository.save(tickets);
                ticketDto = objectMapper.convertValue(ticketObject, TicketDto.class);

                return ticketDto;
            }
        }
        return null;
    }

    public boolean cancelTicket(int pnr){
        Tickets ticket = ticketRepository.findByPnrNumber(pnr);
        if(Objects.nonNull(ticket)) {
            ticket.setStatus("Cancelled");
            Tickets updateTicketStatus = ticketRepository.save(ticket);
            return true;
        }
        return false;
    }

    public TicketDto fetchTicket(int pnr){
        Tickets ticket = ticketRepository.findByPnrNumber(pnr);
        TicketDto ticketDto = objectMapper.convertValue(ticket, TicketDto.class);

        return ticketDto;
    }

    public Integer getTicketCount(Long flightNo){
        Optional<Integer> tickets_count = ticketRepository.countOfFlightBookings(LocalDate.now(), flightNo);
        return tickets_count.orElse(0);
    }

    public Integer getScheduleTicketCount(Long schedule_id){
        Optional<Integer> tickets_count = ticketRepository.countOfScheduleBookings(LocalDate.now(), schedule_id);
        return tickets_count.orElse(0);
    }

    public List<TicketDto> history(){
        List<Tickets> tickets = ticketRepository
                .findByEmail(UserAuth.get().getEmail());

        List<TicketDto> ticketsData= tickets
                .stream()
                .map(ticket -> objectMapper.convertValue(ticket,TicketDto.class))
                .collect(Collectors.toList());

        return ticketsData;
    }


    public List<ResultSet> search(String date, String from, String to){
        String url = adminSettings.getAdminBaseUrl() + "flight/search/" + date +"/" + from +"/"+ to;
        ResponseEntity<ResultSet[]> responseEntity = restTemplate.getForEntity(url, ResultSet[].class);
        ResultSet[] result = responseEntity.getBody();
        LocalDate localDate = LocalDate.parse(date);

        StreamSupport
                .stream(Arrays.stream(result).spliterator(),false)
                .forEach(flight -> {
                    Optional<Integer> booked_business_class_seats = ticketRepository.sumOfBookedTickets(localDate, "Confirmed", "business class", flight.getId());
                    flight.setRemaining_business_class_seats(flight.getBusiness_class_seats() - booked_business_class_seats.orElse(0));
                    Optional<Integer> booked_genearl_class_seats = ticketRepository.sumOfBookedTickets(localDate, "Confirmed", "general class", flight.getId());
                    flight.setRemaining_general_seats(flight.getGeneral_seats() - booked_genearl_class_seats.orElse(0));
                    flight.setRemaining_seat_capacity(flight.getCapacity() - booked_business_class_seats.orElse(0) - booked_genearl_class_seats.orElse(0));
                });

        System.out.println("result is: "+Arrays.asList(result));

        return Arrays.asList(result);
    }
}