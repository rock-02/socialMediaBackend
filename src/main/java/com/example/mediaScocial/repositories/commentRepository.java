package com.example.mediaScocial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mediaScocial.Enity.Comment;

@Repository
public interface commentRepository extends JpaRepository<Comment, Integer> {


    
}
