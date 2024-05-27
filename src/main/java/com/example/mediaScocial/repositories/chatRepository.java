package com.example.mediaScocial.repositories;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mediaScocial.Enity.Chat;
import com.example.mediaScocial.Enity.User;

@Repository
public interface chatRepository extends JpaRepository<Chat, Integer> {

    public List<Chat> findByUsersId(Integer userId);

    @Query("SELECT c FROM Chat c WHERE :user Member of c.users AND :reqUser Member of c.users")
    public Chat findChatByUsersId(@Param("reqUser") User reqUser, @Param("user") User user);
}
