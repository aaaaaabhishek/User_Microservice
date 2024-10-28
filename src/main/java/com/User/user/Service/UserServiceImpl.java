package com.User.user.Service;

import com.User.user.Entity.User;
import com.User.user.Exception.InvalidException;
import com.User.user.Payload.UserDto;
import com.User.user.Repository.UserRepository;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public UserDto saveUser(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);
        return mapper.map(savedUser, UserDto.class);
    }

    @Override
    @Transactional

    public UserDto getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(value -> mapper.map(value, UserDto.class)).orElse(null);
    }

    @Override
    @Transactional
    public boolean deleteUser(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true; // Successfully deleted
        }
        return false; // User not found
    }
    @Override
    public UserDto getUserByUsername(String username) {
        // Replace with actual data retrieval logic, e.g., repository call
        User user= userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return mapper.map(user,UserDto.class);
    }
    @Override
    public boolean isUsernameTaken(String username, String userId) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent() && !user.get().getUser_id().equals(userId);
    }
    @Override
    public UserDto updateUser(String id, UserDto userDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new InvalidException("User not found", HttpStatus.NOT_FOUND));

        existingUser.setUser_name(userDto.getUser_name());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPassword(userDto.getPassword());

        User updatedUser = userRepository.save(existingUser);
        return mapper.map(updatedUser,UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList= userRepository.findAll();
       return userList.stream()
               .map(user -> mapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

}
