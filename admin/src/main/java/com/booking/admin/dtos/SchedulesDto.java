package com.booking.admin.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import lombok.Data;
import org.apache.tomcat.jni.Local;

import java.sql.Time;
import java.time.LocalTime;

@Data
public class SchedulesDto {
    private int id;
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime startTime;
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime endTime;
    private String startFrom;
    private String endTo;
    private String availableDays;
}
