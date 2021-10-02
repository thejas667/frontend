package com.booking.admin.mapper;

import com.booking.admin.data.entities.Schedules;
import com.booking.admin.dtos.SchedulesDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class SchedulesMapper extends ConfigurableMapper {
    protected void configure(MapperFactory factory){
        factory.registerClassMap(factory.classMap(SchedulesDto.class, Schedules.class)
                .byDefault());
    }
}
