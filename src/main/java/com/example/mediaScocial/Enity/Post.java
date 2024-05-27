package com.example.mediaScocial.Enity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer postId;

    private String caption;

    private String image;

    private String video;

    private Date createdAt;

    // @JsonIgnore
    @ManyToOne
    private User user;

    @ManyToMany
    private List<User> likes = new ArrayList<>();

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

}
