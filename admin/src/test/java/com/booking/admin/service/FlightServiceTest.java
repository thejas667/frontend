package com.booking.admin.service;

import com.booking.admin.data.entities.Flight;
import com.booking.admin.data.repos.FlightRepository;
import com.booking.admin.data.repos.SchedulesRepository;
import com.booking.admin.dtos.FlightDto;
import com.booking.admin.mapper.FlightMapper;
import com.booking.admin.mapper.SchedulesMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    @InjectMocks
    FlightService flightService;

    @Mock
    private FlightMapper flightMapper;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private SchedulesMapper schedulesMapper;

    @Mock
    private SchedulesRepository schedulesRepository;

    @Test
    void findByFlightNo() {
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setFlightNo(9292929L);
        flight.setName("deccan");
        when(flightRepository.findByFlightNo(9292929L)).thenReturn(flight);
        FlightDto flightDto = new FlightDto();
        flightDto.setId(flight.getId().intValue());
        flightDto.setFlightNo(flight.getFlightNo());
        flightDto.setName(flight.getName());
        when(flightMapper.map(flight, FlightDto.class)).thenReturn(flightDto);
        FlightDto result = flightService.findByFlightNo(9292929L);
        assertEquals("deccan", result.getName());
    }
}