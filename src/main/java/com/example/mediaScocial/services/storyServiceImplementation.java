package com.example.mediaScocial.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mediaScocial.Enity.Story;
import com.example.mediaScocial.Enity.User;
import com.example.mediaScocial.repositories.storyRepository;

@Service
public class storyServiceImplementation implements storyService {

    @Autowired
    private storyRepository storyRepository;

    @Autowired
    private userService userService;

    @Override
    public Story createStory(Story story, User user) throws Exception {

        Story newStory = new Story();

        newStory.setCaptions(story.getCaptions());
        newStory.setImage(story.getImage());
        newStory.setUser(user);
        newStory.setTimeStamp(new Date(System.currentTimeMillis()));

        Story savedStory = storyRepository.save(newStory);

        return savedStory;

    }

    @Override
    public List<Story> findStoryByUserId(Integer userId) throws Exception {

        User user = userService.findUserById(userId);

        if (user == null)
            throw new Exception("User not found");

        return storyRepository.findByUserId(userId);
    }

}
