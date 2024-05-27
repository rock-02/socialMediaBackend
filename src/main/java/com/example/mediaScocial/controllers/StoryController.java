package com.example.mediaScocial.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.mediaScocial.Enity.Story;
import com.example.mediaScocial.Enity.User;
import com.example.mediaScocial.services.storyService;
import com.example.mediaScocial.services.userService;

@RestController
public class StoryController {

    @Autowired
    private storyService storyService;

    @Autowired
    private userService userService;

    @PostMapping("/api/story")
    public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String token) {

        try {
           User reqUser= userService.findUserByJwt(token);


            return storyService.createStory(story, reqUser);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/api/story/{userId}")
    public List<Story> findStoryByUserId(@PathVariable Integer userId) {
        try {
            return storyService.findStoryByUserId(userId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }



}
