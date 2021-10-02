package com.flighttickets.booking.service;

import com.flighttickets.booking.data.entities.Users;
import com.flighttickets.booking.data.repos.UserRepository;
import com.flighttickets.booking.dtos.JwtRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Value("admin")
    private String globalusername;

    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Users> user = userRepository.findByEmail(username);
        return new User(username,"$2a$12$denAuLFG3y7Sr9cnTZheyeaU/P1zib1LFXjJkmtJuQRXvCmsSraXq",new ArrayList<>());
    }
    public Users userLogin(JwtRequest jwtRequest){
        Optional<Users> user = userRepository.findByEmailAndPassword(jwtRequest.getUsername(), jwtRequest.getPassword());
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }
}
