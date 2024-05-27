package com.example.mediaScocial.services;

import java.util.List;

import com.example.mediaScocial.Enity.Reels;

public interface reelsService {

    public Reels createReel(Reels reel, Integer userId) throws Exception;

    public List<Reels> findReelsByUser(Integer userId) throws Exception;

    public Reels getReelById(Integer reelId) throws Exception;

    public List<Reels> findAllReels();
}
