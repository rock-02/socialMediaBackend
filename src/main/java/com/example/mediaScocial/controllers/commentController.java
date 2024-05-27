package com.example.mediaScocial.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.mediaScocial.Enity.Comment;
import com.example.mediaScocial.Enity.User;
import com.example.mediaScocial.config.jwtProvider;
import com.example.mediaScocial.repositories.userRepository;
import com.example.mediaScocial.services.commentService;

@RestController
public class commentController {
    @Autowired
    private commentService commentService;

    @Autowired
    private userRepository userRepository;

    @PostMapping("/api/comment/post/{PostId}")
    public Comment createComment(@RequestBody Comment comment,
            @RequestHeader("Authorization") String token, @PathVariable("PostId") Integer PostId) throws Exception {

        String email = jwtProvider.getEmailFromJwtToken(token);

        User user = userRepository.findByEmail(email);

        return commentService.createComment(comment, user.getId(), PostId);
    }

    @PutMapping("/api/comment/{commentId}/like")
    public Comment likeComment(@PathVariable("commentId") Integer commentId,
            @RequestHeader("Authorization") String token) throws Exception {

        String email = jwtProvider.getEmailFromJwtToken(token);

        User user = userRepository.findByEmail(email);

        return commentService.likeComment(commentId, user.getId());
    }

    @GetMapping("/api/comment/{commentId}")
    public Comment getCommentById(@PathVariable("commentId") Integer commentId) {
        try {
            Comment comment = commentService.findCommentById(commentId);
            return comment;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
