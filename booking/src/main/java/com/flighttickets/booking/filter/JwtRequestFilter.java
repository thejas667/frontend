package com.flighttickets.booking.filter;

import com.flighttickets.booking.configure.JwtTokenUtil;
import com.flighttickets.booking.service.JwtUserDetailsService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = httpServletRequest.getHeader("Authorization");
        String username;
        String jwtToken;
        Claims userDetails2 = null;

        if(requestTokenHeader != null  && requestTokenHeader.startsWith("Bearer ")){
            jwtToken = requestTokenHeader.substring(7);
            username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            userDetails2 = jwtTokenUtil.getClaimsFromToken(jwtToken, claims -> claims);

            // once we have confirmed the token is valid
            if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null){
//                UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails2,null,null);
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
