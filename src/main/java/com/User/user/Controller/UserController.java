package com.User.user.Controller;

import com.User.user.Payload.UserDto;
import com.User.user.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static Logger logger= LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create a new user
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDto userDto, BindingResult result) {
        if(result.hasErrors()){
            logger.error("Failed to add user. Input data is Invalid");
            List<Map<String, String>> errors = result.getFieldErrors().stream()
                    .map(fieldError -> Map.of(
                            "field", fieldError.getField(),
                            "error", fieldError.getDefaultMessage()
                    ))
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors); // Return a list of all validation errors

        }

        logger.info("Creating user: {}", userDto);
       UserDto savedUser=userService.saveUser(userDto);
       if(savedUser!=null) {
           logger.info("User is added to Database" + userDto.user_id);
           return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
       }
       logger.error("Failed to crete user");
       return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    // Read a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        logger.info("Fetching user with ID: {}", id);
        UserDto fetchUser = userService.getUserById(id);
        if (fetchUser != null) {
            logger.info("user_id:" + 1 + " is found from the database and fetch data from it");
            return new ResponseEntity<>(fetchUser, HttpStatus.OK);
        }
        logger.error("user id:" + id + " is not found in database");
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        logger.info("Deleting user with ID: {}", id);
        if (userService.deleteUser(id)) {
            logger.info("user with this user_id:"+id+" is deleted");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.error("user this user_id:"+id +" is not found");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        logger.info("Fetching user with username: {}", username);
        UserDto userDto = userService.getUserByUsername(username);
        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody @Valid UserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            logger.error("Failed to update user. Input data is invalid.");
            List<Map<String, String>> errors = result.getFieldErrors().stream()
                    .map(fieldError -> Map.of(
                            "field", fieldError.getField(),
                            "error", fieldError.getDefaultMessage()
                    ))
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        logger.info("Updating user with ID: {}", id);
        if (userService.isUsernameTaken(userDto.getUser_name(), id)) {
            logger.error("Username '{}' is already taken.", userDto.getUser_name());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "Username already taken."));
        }

        UserDto updatedUser = userService.updateUser(id, userDto);
        if (updatedUser != null) {
            logger.info("User with ID: {} successfully updated.", id);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        logger.error("Failed to update user with ID: {}", id);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    // Get a list of all users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        logger.info("Fetching list of all users.");
        List<UserDto> users = userService.getAllUsers();
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        logger.warn("No users found.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
}