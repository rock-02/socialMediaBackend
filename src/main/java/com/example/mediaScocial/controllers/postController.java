package com.example.mediaScocial.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.mediaScocial.Enity.Post;
import com.example.mediaScocial.Enity.User;
import com.example.mediaScocial.config.jwtProvider;
import com.example.mediaScocial.services.postService;
import com.example.mediaScocial.services.userService;

@RestController
public class postController {

    @Autowired
    private postService postService;

    @Autowired

    private userService userService;

    @PostMapping("/api/posts")
    public ResponseEntity<Post> createNewPost(@RequestBody Post post, @RequestHeader("Authorization") String token) {
        try {
            String email = jwtProvider.getEmailFromJwtToken(token);

            Integer userId = userService.findUserByEmail(email).getId();
            Post newPost = postService.createNewPost(post, userId);
            return new ResponseEntity<>(newPost, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/posts/user/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable("postId") Integer postId) {
        try {
            Post post = postService.findPostById(postId);
            return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/api/posts")
    public ResponseEntity<List<Post>> getAllPost() {
        try {
            List<Post> posts = postService.getAllPost();
            return new ResponseEntity<>(posts, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/api/posts/users/{userId}")
    public ResponseEntity<List<Post>> getPostByUserId(@PathVariable("userId") Integer userId) {
        try {
            List<Post> posts = postService.findPostByUserId(userId);
            return new ResponseEntity<>(posts, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/posts/{postId}/users")
    public ResponseEntity<String> deletePost(@PathVariable("postId") Integer postId,
            @RequestHeader("Authorization") String token) {
        try {
            String email = jwtProvider.getEmailFromJwtToken(token);

            Integer userId = userService.findUserByEmail(email).getId();
            String message = postService.deletePost(postId, userId);
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PutMapping("/posts/save/{postId}/user")
    public ResponseEntity<Post> savedPost(@PathVariable("postId") Integer postId,
            @RequestHeader("Authorization") String token) {
        try {
            String email = jwtProvider.getEmailFromJwtToken(token);
            Integer userId = userService.findUserByEmail(email).getId();
            Post post = postService.savedPost(userId, postId);
            return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PutMapping("/api/posts/like/{postId}")
    public ResponseEntity<Post> likePost(@PathVariable("postId") Integer postId,
            @RequestHeader("Authorization") String token) {
        try {

            System.out.println(postId);
            System.out.println();
            System.out.println();
            System.out.println();

            String email = jwtProvider.getEmailFromJwtToken(token);
            User user = userService.findUserByJwt(token);

            System.out.println(email + "   " + user);
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            Post post = postService.likePost(postId, user);
            return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/posts/savedposts")
    public ResponseEntity<List<Post>> getSavedPOstsOfUser(@RequestHeader("Authorization") String token) {

        try {

            String email = jwtProvider.getEmailFromJwtToken(token);

            Integer userId = userService.findUserByEmail(email).getId();
            System.out.println(userId);
            List<Post> posts = postService.getSavedPosts(userId);

            return new ResponseEntity<>(posts, HttpStatus.ACCEPTED);

        } catch (Exception e) {

            System.err.println("Error");

            return null;
        }
    }
}
