package com.flighttickets.booking.mapper;

import com.flighttickets.booking.data.entities.Passenger;
import com.flighttickets.booking.data.entities.Tickets;
import com.flighttickets.booking.dtos.PassengerDto;
import com.flighttickets.booking.dtos.TicketDto;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper extends ConfigurableMapper {
    protected void configure(MapperFactory factory) {
        super.configure(factory);
        factory.registerClassMap(
                factory.classMap(TicketDto.class, Tickets.class)
                        .byDefault()
                        .customize(new PassengerCustomMapper())
                        .toClassMap());

    }
}

class PassengerCustomMapper extends CustomMapper<TicketDto, Tickets> {

    @Override
    public void mapAtoB(final TicketDto ticketDto,final Tickets tickets, MappingContext context) {
        tickets.getPassenger().clear();
        ticketDto.getPassenger().forEach(passengerDto->{
            Passenger passenger = new Passenger();
            passenger.setId(passengerDto.getId());
            passenger.setName(passengerDto.getName());
            passenger.setGender(passengerDto.getGender());
            passenger.setAge(passengerDto.getAge());
            passenger.setMeal(passengerDto.getMeal());
            tickets.getPassenger().add(passenger);
        });
    }

    @Override
    public void mapBtoA(final Tickets tickets, final TicketDto ticketDto,MappingContext context) {
        ticketDto.getPassenger().clear();
        tickets.getPassenger().forEach(passenger->{
            PassengerDto passengerDto = new PassengerDto();
            passengerDto.setName(passenger.getName());
            passengerDto.setGender(passenger.getGender());
            passengerDto.setAge(passenger.getAge());
            passengerDto.setMeal(passenger.getMeal());
            ticketDto.getPassenger().add(passengerDto);
        });
    }
}
