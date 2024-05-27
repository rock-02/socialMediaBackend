package com.example.mediaScocial.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mediaScocial.Enity.Story;

public interface storyRepository extends JpaRepository<Story, Integer> {

    public List<Story> findByUserId(Integer UserId);

    
}
