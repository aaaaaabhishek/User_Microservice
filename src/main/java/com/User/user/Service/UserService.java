package com.User.user.Service;

import com.User.user.Payload.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDto saveUser(UserDto userDto); // Save a new user
    UserDto getUserById(String id); // Retrieve a user by ID
    boolean deleteUser(String id); // Delete a user by ID
    UserDto getUserByUsername(String username);
    boolean isUsernameTaken(String username, String userId); // Check if a username is unique
    UserDto updateUser(String id, UserDto userDto); // Update an existing user
    List<UserDto> getAllUsers();                    // Get a list of all users

}

