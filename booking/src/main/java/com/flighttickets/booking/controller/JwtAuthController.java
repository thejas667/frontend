package com.flighttickets.booking.controller;

import com.flighttickets.booking.configure.JwtTokenUtil;
import com.flighttickets.booking.data.UserAuth;
import com.flighttickets.booking.data.entities.Users;
import com.flighttickets.booking.dtos.JwtRequest;
import com.flighttickets.booking.dtos.JwtResponse;
import com.flighttickets.booking.dtos.LoginFailed;
import com.flighttickets.booking.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/hello")
    public String helloone(){
        return "hello world";
    }

    @GetMapping("/check")
    public ResponseEntity check(){
        return new ResponseEntity(UserAuth.get(),HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest request) throws Exception{
//        authenticate(request.getUsername(),request.getPassword());
//        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(request.getUsername());
        Users users = jwtUserDetailsService.userLogin(request);
        if(users == null){
            return new ResponseEntity(new LoginFailed("Login Failed"), HttpStatus.NOT_FOUND);
        }
        final String token = jwtTokenUtil.generateToken(users);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username,String password) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (DisabledException ex){
            throw new Exception("USER DISABLED ",ex);
        }catch (BadCredentialsException ex){
            throw new Exception("INVALID CREDENTIALS",ex);
        }
    }
}
