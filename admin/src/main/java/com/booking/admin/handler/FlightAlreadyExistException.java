package com.booking.admin.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class FlightAlreadyExistException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public FlightAlreadyExistException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }
}
