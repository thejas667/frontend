package com.booking.admin.mapper;

import com.booking.admin.data.entities.Flight;
import com.booking.admin.data.entities.Schedules;
import com.booking.admin.dtos.FlightDto;
import com.booking.admin.dtos.SchedulesDto;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper extends ConfigurableMapper {
    protected void configure(MapperFactory factory){
        factory.registerClassMap(factory.classMap(FlightDto.class, Flight.class)
                .byDefault()
                .customize(new SchedulesCustomMapper())
                .toClassMap());
    }
}

class SchedulesCustomMapper extends CustomMapper<FlightDto, Flight> {

    @Override
    public void mapAtoB(final FlightDto flightDto,final Flight flight, MappingContext context) {
        flight.getSchedules().clear();
        flightDto.getSchedules().forEach(schedulesDto->{
            Schedules schedules = new Schedules();
            schedules.setId(schedulesDto.getId());
            schedules.setStartTime(schedulesDto.getStartTime());
            schedules.setEndTime(schedulesDto.getEndTime());
            schedules.setStartFrom(schedulesDto.getStartFrom());
            schedules.setEndTo(schedulesDto.getEndTo());
            schedules.setAvailableDays(schedulesDto.getAvailableDays());
            flight.getSchedules().add(schedules);
        });
    }

    @Override
    public void mapBtoA(final Flight flight, final FlightDto flightDto,MappingContext context) {
        flightDto.getSchedules().clear();
        flight.getSchedules().forEach(schedules->{
            SchedulesDto schedulesDto = new SchedulesDto();
            schedulesDto.setId(schedules.getId());
            schedulesDto.setStartTime(schedules.getStartTime());
            schedulesDto.setEndTime(schedules.getEndTime());
            schedulesDto.setStartFrom(schedules.getStartFrom());
            schedulesDto.setEndTo(schedules.getEndTo());
            schedulesDto.setAvailableDays(schedules.getAvailableDays());
            flightDto.getSchedules().add(schedulesDto);
        });
    }
}
