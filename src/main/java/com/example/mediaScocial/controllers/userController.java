package com.example.mediaScocial.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mediaScocial.Enity.User;
import com.example.mediaScocial.config.jwtProvider;
import com.example.mediaScocial.repositories.userRepository;
import com.example.mediaScocial.services.userService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class userController {
    @Autowired
    private userService userService;

    @Autowired
    private userRepository userRepository;

    @GetMapping("/")
    public String home() {
        return "Hello World";
    }

    @GetMapping("/api/users/{userId}")
    public User getUserById(@PathVariable("userId") Integer userId) {
        try {
            User user = userService.findUserById(userId);
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PutMapping("/api/users")
    public User updateUser(@RequestBody User user, @RequestHeader("Authorization") String token) {
        try {
            String email = jwtProvider.getEmailFromJwtToken(token);

            Integer userId = userRepository.findByEmail(email).getId();

            User updatedUser = userService.updateUser(user, userId);
            return updatedUser;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/api/users/follow/{userId2}")
    public User followUser(@RequestHeader("Authorization") String jwt, @PathVariable("userId2") Integer userId2) {
        try {
            String email = jwtProvider.getEmailFromJwtToken(jwt);

            Integer userId1 = userRepository.findByEmail(email).getId();
            User user = userService.followUser(userId1, userId2);
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/api/users/search")
    public List<User> searchUser(@RequestParam String query) {
        try {
            List<User> users = userService.searchUser(query);
            return users;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/api/users/profile")
    public User getProfile(@RequestHeader("Authorization") String token) {
        try {
            String email = jwtProvider.getEmailFromJwtToken(token);

            User user = userRepository.findByEmail(email);

            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
