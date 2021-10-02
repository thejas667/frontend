package com.booking.admin.service;

import com.booking.admin.data.entities.Flight;
import com.booking.admin.data.repos.FlightRepository;
import com.booking.admin.data.repos.SchedulesRepository;
import com.booking.admin.dtos.FlightDto;
import com.booking.admin.dtos.ResultSet;
import com.booking.admin.handler.FlightAlreadyExistException;
import com.booking.admin.mapper.FlightMapper;
import com.booking.admin.mapper.SchedulesMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FlightService {


    @Autowired
    private FlightMapper flightMapper;

    @Autowired
    private SchedulesMapper schedulesMapper;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private SchedulesRepository schedulesRepository;

    @Autowired
    private RestTemplate restTemplate;

    @PersistenceContext
    EntityManager entityManager;

    static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }
    public FlightDto create(FlightDto flightDto) {

        Flight flight = objectMapper.convertValue(flightDto, Flight.class);
        Flight flightObject;
        if (flightDto.getCapacity() != (flightDto.getBusinessClassSeats() + flightDto.getGeneralSeats())) {
            throw new FlightAlreadyExistException("Capacity doesn't match");
        }
        try {
            flightObject = flightRepository.save(flight);
        } catch (Exception ex) {
            throw new FlightAlreadyExistException("Flight already exist" +ex.getMessage());
        }
        flightDto = objectMapper.convertValue(flightObject, FlightDto.class);
        return flightDto;
    }
    public FlightDto update(Long flightNo, FlightDto flightDto) {
        System.out.println("flightNo "+ flightNo);
        flightDto.setFlightNo(flightNo);
        return create(flightDto);
    }

    public FlightDto findByFlightNo(Long flightNo) {
        Flight flight = flightRepository.findByFlightNo(flightNo);
        FlightDto flightData = flightMapper.map(flight, FlightDto.class);

        return flightData;
    }

    public List<FlightDto> findFlights() {

        List<FlightDto> flights = flightRepository.findAll()
                .stream()
                .map(flight -> flightMapper.map(flight, FlightDto.class))
                .collect(Collectors.toList());
        return flights;
    }

    public boolean deleteFlights(Long flightNo) {
        Flight flight = flightRepository.findByFlightNo(flightNo);
        String url = "http://localhost:8091/ticket/booked/count/"+ flightNo;
        ResponseEntity<Integer> responseEntity = restTemplate.getForEntity(url, Integer.class);
//        int result = responseEntity.getBody();
        if(responseEntity.getBody().intValue() > 0){
            throw new FlightAlreadyExistException("Bookings are exist with this flight number");
        }
        flightRepository.delete(flight);
        return true;
    }

    public List<ResultSet> search(String date, String from, String to) {
        LocalDate localDate = LocalDate.parse(date);
        String weekName = localDate.getDayOfWeek().toString();
        Query query = entityManager.createNativeQuery("select f.id as fid,f.business_class_seat_price,f.business_class_seats,f.capacity," +
                "f.flight_no, f.general_seat_price,f.general_seats,f.name,f.logo, fs.* " +
                "from flight_schedules fs join flight_details f on f.id=fs.flight_id where fs.start_from = '"+from+"' and fs.end_to ='"+to+"' and fs.available_days LIKE '%"+weekName+"%'", ResultSet.class);
        List<ResultSet> resultSets = (List<ResultSet>) query.getResultList();

        return resultSets;
    }

    public ResultSet searchSchedule(int id) {
        Query query = entityManager.createNativeQuery("select f.id as fid,f.business_class_seat_price,f.business_class_seats,f.capacity," +
                "f.flight_no, f.general_seat_price,f.general_seats,f.name,f.logo, fs.* " +
                "from flight_schedules fs join flight_details f on f.id=fs.flight_id where fs.id = '"+id+"'", ResultSet.class);
        ResultSet resultSets = (ResultSet) query.getSingleResult();

        return resultSets;
    }
}
