package com.example.mediaScocial.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.mediaScocial.Enity.Reels;
import com.example.mediaScocial.config.jwtProvider;
import com.example.mediaScocial.repositories.userRepository;
import com.example.mediaScocial.services.reelsService;

@RestController
public class reelsController {
    @Autowired
    private reelsService reelsService;

    @Autowired
    private userRepository userRepository;

    @PostMapping("/api/reels")
    public Reels createReels(@RequestBody Reels reel, @RequestHeader("Authorization") String token) {

        try {
            String email = jwtProvider.getEmailFromJwtToken(token);
            Integer userId = userRepository.findByEmail(email).getId();
            return reelsService.createReel(reel, userId);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/api/reels")
    public List<Reels> findAllReels() {
        try {
            return reelsService.findAllReels();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/api/reels/user/{userId}")
    public List<Reels> findReelsByUserId(@PathVariable("userId") Integer userId) {
        try {
            return reelsService.findReelsByUser(userId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/api/reels/{reelId}")
    public Reels getReelById(@PathVariable("reelId") Integer reelId) {
        try {
            return reelsService.getReelById(reelId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
}
