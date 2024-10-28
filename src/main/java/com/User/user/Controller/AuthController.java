package com.User.user.Controller;
import com.User.user.Entity.User;
import com.User.user.Payload.JWTAuthResponse;
import com.User.user.Payload.LoginDto;
import com.User.user.Payload.UserDto;
import com.User.user.Repository.RoleReposotory;
import com.User.user.Repository.UserRepository;
import com.User.user.Security.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleReposotory roleReposotoryry;
    private final JwtTokenProvider tokenProvider;
    private final ModelMapper mapper;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, RoleReposotory roleReposotoryry, JwtTokenProvider tokenProvider, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.roleReposotoryry = roleReposotoryry;
        this.tokenProvider = tokenProvider;
        this.mapper = mapper;
    }

    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateuser(@RequestBody LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword())
        );//authenticate method compare actual value with expected value
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //get token from tokenProvider
        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody UserDto signupDto) {
        // Check if email already exists
        if (userRepository.existsByEmail(signupDto.getEmail())) {
            return new ResponseEntity<>("Email already exists: " + signupDto.getEmail(), HttpStatus.CONFLICT);
        }
        // Check if username already exists
        if (userRepository.existsByUsername(signupDto.getUser_name())) {
            return new ResponseEntity<>("Username already exists: " + signupDto.getUser_name(), HttpStatus.CONFLICT);
        }

        // Map DTO to Entity
        User user = mapper.map(signupDto, User.class);

             // Encode the password
        String encodedPassword = passwordEncoder.encode(user.password);

        user.setPassword(encodedPassword);

        // Save user to the database
        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}