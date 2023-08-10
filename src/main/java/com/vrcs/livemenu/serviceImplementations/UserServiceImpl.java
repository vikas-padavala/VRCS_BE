package com.vrcs.livemenu.serviceImplementations;

import com.vrcs.livemenu.entities.User;
import com.vrcs.livemenu.exceptions.UserNotFound;
import com.vrcs.livemenu.exceptions.UserWithEmailAlreadyExists;
import com.vrcs.livemenu.payloads.UserDto;
import com.vrcs.livemenu.repositories.UserRepository;
import com.vrcs.livemenu.services.UserService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userDtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles("USER");
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserWithEmailAlreadyExists(
                    "user with email id " + user.getEmail() + " already exists in the system!");
        }
        return userToUserDto(user);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound("user with user id " + userId + " not found!"));
        // User existingUser = userRepository.findById(updatedUser.getId());
        if (userDto.getEmail() != null)
            user.setEmail(userDto.getEmail());
        if (userDto.getFirstName() != null)
            user.setFirstName(userDto.getFirstName());
        if (userDto.getLastName() != null)
            user.setLastName(userDto.getLastName());
        if (userDto.getRoles() != null)
            user.setRoles(userDto.getRoles());
        if (userDto.getPassword() != null)
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return userToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<UserDto>(users.size());
        for (User user : users) {
            userDtos.add(userToUserDto(user));
        }
        return userDtos;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("user with this email is not present in the system"));
        return userToUserDto(user);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user with this email is not present in the system"));
        return userToUserDto(user);
    }

    private User userDtoToUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRoles(userDto.getRoles());
        return user;
    }

    private UserDto userToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRoles(user.getRoles());
        return userDto;
    }

}
