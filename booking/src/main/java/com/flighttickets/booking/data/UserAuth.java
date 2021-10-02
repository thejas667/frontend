package com.flighttickets.booking.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserAuth {

    static ObjectMapper objectMapper = new ObjectMapper();
    public static TokenDetails get(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Claims claims = (Claims) authentication.getPrincipal();
        TokenDetails auth = objectMapper.convertValue(claims,TokenDetails.class);
        return auth;
    }
}