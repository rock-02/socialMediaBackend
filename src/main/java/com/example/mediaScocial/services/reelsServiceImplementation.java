package com.example.mediaScocial.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mediaScocial.Enity.Reels;
import com.example.mediaScocial.Enity.User;

import com.example.mediaScocial.repositories.reelsRepository;

@Service
public class reelsServiceImplementation implements reelsService {

    @Autowired
    private userService userService;

    @Autowired
    private reelsRepository reelsRepository;

    @Override
    public Reels createReel(Reels reel, Integer userId) throws Exception {
        User user = userService.findUserById(userId);

        Reels newReel = new Reels();

        newReel.setReel(reel.getReel());
        newReel.setTitle(reel.getTitle());
        newReel.setUser(user);
        newReel.setCreatedAt(new Date(System.currentTimeMillis()));

        Reels savedReel = reelsRepository.save(newReel);

        return savedReel;
    }

    @Override
    public List<Reels> findReelsByUser(Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        if (user == null) {
            throw new Exception("User not found with Id : " + userId);
        }
        List<Reels> reels = reelsRepository.findByUserId(userId);
        return reels;
    }

    @Override
    public Reels getReelById(Integer reelId) throws Exception {

        Optional<Reels> reel = reelsRepository.findById(reelId);

        if (reel.isPresent()) {
            return reel.get();
        } else {
            throw new Exception("Reel not found with Id : " + reelId);
        }

    }

    @Override
    public List<Reels> findAllReels() {

        List<Reels> reels = reelsRepository.findAll();

        return reels;
    }

}
