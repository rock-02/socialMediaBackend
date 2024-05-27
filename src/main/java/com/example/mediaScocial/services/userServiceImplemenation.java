package com.example.mediaScocial.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mediaScocial.Enity.User;
import com.example.mediaScocial.config.jwtProvider;
import com.example.mediaScocial.repositories.userRepository;

@Service
public class userServiceImplemenation implements userService {
    @Autowired
    private userRepository userRepository;

    @Override
    public User regesterUser(User user) throws Exception {

        return userRepository.save(user);
    }

    @Override
    public User findUserById(Integer userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new Exception("User not found with Id : " + userId);
        }

    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        return userRepository.findByEmail(email);
    }

    @Override
    public User updateUser(User user, Integer userId) throws Exception {

        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User userToUpdate = userOptional.get();
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setGender(user.getGender());
            userToUpdate.setBackgroundPicture(user.getBackgroundPicture());
            userToUpdate.setProfilePicture(user.getProfilePicture());
            userToUpdate.setBio(user.getBio());
            userToUpdate.setUserName(user.getUserName());
            return userRepository.save(userToUpdate);

        } else {
            throw new Exception("User not found with Id : " + userId);
        }

    }

    @Override
    public User followUser(Integer userId, Integer followId) throws Exception {

        User user1 = userRepository.findById(userId).get();

        User user2 = userRepository.findById(followId).get();

        if (user1.getFollowing().contains(followId)) {
            user1.getFollowing().remove(followId);
            user2.getFollowers().remove(userId);
            userRepository.save(user1);
            userRepository.save(user2);
            return user1;
        }

        user1.getFollowing().add(followId);

        user2.getFollowers().add(userId);

        userRepository.save(user1);
        userRepository.save(user2);

        return user1;

    }

    @Override
    public List<User> searchUser(String query) throws Exception {

        List<User> users = userRepository.searchUser(query);

        return users;
    }

    @Override
    public User findUserByJwt(String jwt) {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        return userRepository.findByEmail(email);
    }

}
