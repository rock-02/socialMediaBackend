package com.example.mediaScocial.services;

import java.util.List;

import com.example.mediaScocial.Enity.User;

public interface userService {

    public User regesterUser(User user) throws Exception;

    public User findUserById(Integer userId) throws Exception;

    public User findUserByEmail(String email) throws Exception;

    public User updateUser(User user, Integer userId) throws Exception;

    public User followUser(Integer userId, Integer followId) throws Exception;

    // public User unfollowUser(Integer userId, Integer followId) throws Exception;
    public List<User> searchUser(String query) throws Exception;

    public User findUserByJwt(String jwt);

}
