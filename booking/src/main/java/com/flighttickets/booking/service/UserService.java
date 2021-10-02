package com.flighttickets.booking.service;

import com.flighttickets.booking.data.UserAuth;
import com.flighttickets.booking.data.entities.Users;
import com.flighttickets.booking.data.repos.UserRepository;
import com.flighttickets.booking.dtos.UserDto;
import com.flighttickets.booking.mapper.UserMapper;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper Mapper;
    public UserService(UserRepository userRepository, UserMapper Mapper){
        this.userRepository = userRepository;
        this.Mapper = Mapper;
    }
    public UserDto register(UserDto userDto){
        Users users = Mapper.map(userDto, Users.class);
        Users register = userRepository.save(users);
        UserDto registeredUserDto = Mapper.map(register, UserDto.class);
        return registeredUserDto;
    }
    public UserDto findUserByEmail(String email){
        Users users = userRepository.findByEmail(email);
        UserDto userData = Mapper.map(users, UserDto.class);
        return userData;
    }

    public UserDto findUser(){
        Users users = userRepository.getById(UserAuth.get().getUserId());
        UserDto userDto = Mapper.map(users, UserDto.class);
        return userDto;
    }

    public boolean deleteUser(int id){
        userRepository.deleteById(id);
        return true;
    }
    public UserDto updateUser(UserDto userDto){
        Users user = userRepository.findByEmail(userDto.getEmail());
        if(Objects.nonNull(user)){
            Users userDtoData = Mapper.map(userDto, Users.class);
            userDtoData.setId(user.getId());
            Users updateUser = userRepository.save(userDtoData);
            UserDto data = Mapper.map(updateUser, UserDto.class);
            return data;
        }
        return null;
    }
}
