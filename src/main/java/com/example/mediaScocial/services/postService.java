package com.example.mediaScocial.services;

import java.util.List;
import com.example.mediaScocial.Enity.Post;
import com.example.mediaScocial.Enity.User;

public interface postService {

    public Post createNewPost(Post post, Integer userId) throws Exception;

    public Post findPostById(Integer postId) throws Exception;

    public String deletePost(Integer postId, Integer userID) throws Exception;

    public List<Post> getAllPost() throws Exception;

    public List<Post> findPostByUserId(Integer userId) throws Exception;

    public Post savedPost(Integer userId, Integer postId) throws Exception;

    public Post likePost(Integer postId, User user) throws Exception;

    public List<Post> getSavedPosts(Integer userId) throws Exception;

}
