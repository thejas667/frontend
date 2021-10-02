package com.flighttickets.booking.mapper;

import com.flighttickets.booking.data.entities.Users;
import com.flighttickets.booking.dtos.UserDto;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class UserMapper extends ConfigurableMapper {
    protected void configure(MapperFactory factory) {
        factory.registerClassMap(factory.classMap(Users.class, UserDto.class)
                .byDefault());
    }
}
