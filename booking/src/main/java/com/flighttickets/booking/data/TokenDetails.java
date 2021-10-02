package com.flighttickets.booking.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenDetails {

    private Integer userId;
    private String name;
    private String email;
    private String sub;
    private long exp;
    private long iat;
}
