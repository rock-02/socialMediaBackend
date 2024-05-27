package com.example.mediaScocial.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mediaScocial.Enity.User;
import com.example.mediaScocial.config.jwtProvider;
import com.example.mediaScocial.models.LoginRequest;
import com.example.mediaScocial.repositories.userRepository;
import com.example.mediaScocial.services.customUserDetailService;
import com.example.response.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private userRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    customUserDetailService customUserDetailService;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {

        User userExists = userRepository.findByEmail(user.getEmail());

        if (userExists != null) {
            throw new Exception("User already exists");
        }

        User regUser = new User();

        regUser.setEmail(user.getEmail());

        regUser.setFirstName(user.getFirstName());

        regUser.setGender(user.getGender());

        regUser.setLastName(user.getLastName());

        regUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(regUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
                savedUser.getPassword());

        String token = jwtProvider.generateToken(authentication);

        // sSystem.out.println("Token : " + token);

        // String token = "Token";
        AuthResponse authResponse = new AuthResponse(token, "User registered successfully");

        return authResponse;

    }

    @PostMapping("/signin")
    public AuthResponse LoginUser(@RequestBody LoginRequest loginRequest) throws Exception {

        Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse(token, "User logged in successfully");

        return authResponse;

    }

    private Authentication authenticate(String email, String password) {

        UserDetails userDetails = customUserDetailService.loadUserByUsername(email);

        if (userDetails == null) {
            throw new BadCredentialsException("User not found with email : " + email);
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password/email");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

    }

}
