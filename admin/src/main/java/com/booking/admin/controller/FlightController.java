package com.booking.admin.controller;

import com.booking.admin.dtos.FlightDto;
import com.booking.admin.dtos.ResultSet;
import com.booking.admin.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "*")

@RequestMapping("/flight")
@RestController
@CrossOrigin(origins = "*")
public class FlightController {

    @Autowired
    private FlightService flightService;

    //@PreAuthorize("")
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World";
    }

    @PostMapping
    public FlightDto addFlight(@RequestBody FlightDto flightDto){return flightService.create(flightDto);}

    @GetMapping("/{flightNo}")
    public FlightDto findByFlightNo(@PathVariable Long flightNo){return flightService.findByFlightNo(flightNo);}

    @GetMapping
    public List<FlightDto> findFlights(){return flightService.findFlights();}

    @DeleteMapping("/{flightNo}")
    public boolean deleteFlights(@PathVariable Long flightNo){return flightService.deleteFlights(flightNo);}

    @PutMapping("/{flightNo}")
    public FlightDto updateFlight(@PathVariable Long flightNo, @RequestBody FlightDto flightDto){
        return flightService.update(flightNo, flightDto);
    }

    @GetMapping("/search/{date}/{from}/{to}")
    public ResponseEntity<?> search(@PathVariable String date, @PathVariable String from, @PathVariable String to){
        List<ResultSet> resultSets = flightService.search(date, from,to);
        return new ResponseEntity<>(resultSets, HttpStatus.OK);
    }
    @GetMapping("/search/schedule/{id}")
    public ResponseEntity<?> search(@PathVariable int id){
        ResultSet resultSets = flightService.searchSchedule(id);
        return new ResponseEntity<>(resultSets, HttpStatus.OK);
    }

}

// POST api - to generate JWT
// POST/GET api - to validate JWT via header


