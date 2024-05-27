package com.example.mediaScocial.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.mediaScocial.Enity.Chat;
import com.example.mediaScocial.Enity.User;
// import com.example.mediaScocial.models.chatRequest;
import com.example.mediaScocial.services.chatService;
import com.example.mediaScocial.services.userService;

@RestController
public class chatController {

    @Autowired
    private chatService chatService;

    @Autowired
    private userService userService;

    @PostMapping("/api/chat/{userId}")
    public Chat createChat(@RequestHeader("Authorization") String token, @PathVariable Integer userId) {

        try {
            User reqUser = userService.findUserByJwt(token);

            System.out.println(userId);
            System.out.println();
            // System.out.println(req);
            System.out.println();
            System.out.println();
            return chatService.createChat(reqUser, userId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/api/chat/{chatId}")

    public Chat findChatById(@PathVariable("chatId") Integer chatId) {
        try {
            return chatService.findChatById(chatId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/api/chats")

    public List<Chat> findUsersChat(@RequestHeader("Authorization") String token) {
        try {
            User reqUser = userService.findUserByJwt(token);
            return chatService.findUsersChat(reqUser.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
