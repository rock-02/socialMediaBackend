package com.example.mediaScocial.Enity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String firstName;

    String bio;

    String userName;

    String profilePicture;

    String backgroundPicture;

    String lastName;

    String email;

    String password;

    String gender;

    List<Integer> followers = new ArrayList<>();

    List<Integer> following = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    List<Post> savedPosts = new ArrayList<>();
}
