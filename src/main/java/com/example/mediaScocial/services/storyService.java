package com.example.mediaScocial.services;

import java.util.List;

import com.example.mediaScocial.Enity.Story;
import com.example.mediaScocial.Enity.User;

public interface storyService {

    public Story createStory(Story story, User user) throws Exception;

    public List<Story> findStoryByUserId(Integer userId) throws Exception;

}
