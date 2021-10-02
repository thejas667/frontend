package com.flighttickets.booking.controller;

import com.flighttickets.booking.dtos.ResultSet;
import com.flighttickets.booking.dtos.TicketDto;
import com.flighttickets.booking.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/ticket")
@RestController
@CrossOrigin
public class TicketController {
    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    @PostMapping("booking/{flightNo}")
    public TicketDto booking(@PathVariable Long flightNo, @RequestBody TicketDto ticketDto){return ticketService.booking(flightNo, ticketDto);}

    @DeleteMapping("booking/{pnr}")
    public boolean cancelTicket(@PathVariable int pnr){return ticketService.cancelTicket(pnr);}

    @GetMapping("booked/count/{flightNo}")
    public Integer getTicketCount(@PathVariable Long flightNo){return ticketService.getTicketCount(flightNo);}

    @GetMapping("/schedule/booked/count/{schedule_id}")
    public Integer getScheduleTicketCount(@PathVariable Long schedule_id){return ticketService.getScheduleTicketCount(schedule_id);}

    @GetMapping("/{pnr}")
    public TicketDto fetchTicket(@PathVariable int pnr){return ticketService.fetchTicket(pnr);}

    @GetMapping("/history")
    public  List<TicketDto> history(){return ticketService.history();}

    @GetMapping("/search/{date}/{from}/{to}")
    public ResponseEntity<?> search(@PathVariable String date, @PathVariable String from, @PathVariable String to){
        List<ResultSet> resultSets = ticketService.search(date, from,to);
        return new ResponseEntity<>(resultSets, HttpStatus.OK);
    }
}
