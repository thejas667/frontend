package com.flighttickets.booking.controller;

import com.flighttickets.booking.dtos.UserDto;
import com.flighttickets.booking.service.UserService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
@CrossOrigin
public class UserController {
    private UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public UserDto register(@RequestBody UserDto userDto){return userService.register(userDto);}

    @GetMapping("{email}")
    public UserDto findUserByEmail(@PathVariable String email){return userService.findUserByEmail(email);}

    @GetMapping
    public UserDto findUser(){return userService.findUser();}

    @DeleteMapping("{id}")
    public boolean deleteUser(@PathVariable int id){return userService.deleteUser(id);}

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto){return userService.updateUser(userDto);}
}
