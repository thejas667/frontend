package com.flighttickets.booking.mapper;

import com.flighttickets.booking.data.entities.Passenger;
import com.flighttickets.booking.dtos.PassengerDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class PassengerMapper extends ConfigurableMapper {
    protected void configure(MapperFactory factory){
        factory.registerClassMap(factory.classMap(Passenger.class, PassengerDto.class)
                .byDefault());
    }
}
