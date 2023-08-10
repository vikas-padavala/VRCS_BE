package com.vrcs.livemenu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vrcs.livemenu.payloads.UserDto;
import com.vrcs.livemenu.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<List<UserDto>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Long userId) {
        return new ResponseEntity<UserDto>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PostMapping("/registerUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        try {
            return new ResponseEntity<UserDto>(userService.createUser(userDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<UserDto>(userDto, HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") Long userId, @RequestBody UserDto userDto) {
        return new ResponseEntity<UserDto>(userService.updateUser(userId, userDto), HttpStatus.ACCEPTED);
    }

}
