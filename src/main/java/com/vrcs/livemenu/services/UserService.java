package com.vrcs.livemenu.services;

import java.util.List;

import com.vrcs.livemenu.payloads.UserDto;

public interface UserService {
    public UserDto createUser(UserDto userDto);

    public List<UserDto> getAllUsers();

    public UserDto getUserByEmail(String email);

    public UserDto updateUser(Long userId, UserDto userDto);

    public UserDto getUserById(Long id);
}
