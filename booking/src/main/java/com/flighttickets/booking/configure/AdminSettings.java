package com.flighttickets.booking.configure;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class AdminSettings {
    @Value("${external.service.hello.url}")
    private String adminBaseUrl;
}
