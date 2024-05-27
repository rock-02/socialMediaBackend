package com.example.mediaScocial.services;

import com.example.mediaScocial.repositories.commentRepository;
import com.example.mediaScocial.repositories.postRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mediaScocial.Enity.Comment;
import com.example.mediaScocial.Enity.Post;
import com.example.mediaScocial.Enity.User;

import java.sql.Date;
import java.util.Optional;

@Service
public class commentServiceImplementation implements commentService {
    @Autowired
    private postService postService;

    @Autowired
    private userService userService;

    @Autowired
    private commentRepository commentRepository;

    @Autowired 
    private postRepository postRepository;

    @Override
    public Comment createComment(Comment comment,
            Integer userId,
            Integer PostId) throws Exception {
        User user = userService.findUserById(userId);

        Post post = postService.findPostById(PostId);

        System.out.println("POst Is printing " + post);

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        Comment newComment = new Comment();
        newComment.setContent(comment.getContent());
        newComment.setUser(user);
        newComment.setCreatedAt(new Date(System.currentTimeMillis()));

        Comment savedComment = commentRepository.save(newComment);

        post.getComments().add(savedComment);

        postRepository.save(post);

        return savedComment;
    }

    @Override
    public Comment likeComment(Integer commentId, Integer UserID) throws Exception {
        Comment comment = findCommentById(commentId);

        User user = userService.findUserById(UserID);

        if (comment.getLikes().contains(user)) {
            comment.getLikes().remove(user);
        } else {
            comment.getLikes().add(user);
        }

        return commentRepository.save(comment);
    }

    @Override
    public Comment findCommentById(Integer commentId) throws Exception {

        Optional<Comment> comment = commentRepository.findById(commentId);

        if (comment.isEmpty()) {
            throw new Exception("Comment not found with " + commentId);
        }

        return comment.get();
    }

}
