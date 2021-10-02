package com.flighttickets.booking.service;

import com.flighttickets.booking.data.entities.Users;
import com.flighttickets.booking.data.repos.UserRepository;
import com.flighttickets.booking.dtos.UserDto;
import com.flighttickets.booking.mapper.UserMapper;
import javafx.beans.binding.When;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper Mapper;

    @Test
    void findUserByEmail() {
        Users user = new Users();
        user.setId(1);
        user.setEmail("test@gmail.com");
        user.setName("test");
        when(userRepository.findByEmail("test@gmail.com")).thenReturn(user);
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        when(Mapper.map(user, UserDto.class)).thenReturn(userDto);
        UserDto result = userService.findUserByEmail("test@gmail.com");
        assertEquals("test", result.getName());
    }
}