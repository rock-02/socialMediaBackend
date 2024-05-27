package com.example.mediaScocial.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.mediaScocial.Enity.Message;
import com.example.mediaScocial.Enity.User;
import com.example.mediaScocial.services.messgaeService;
import com.example.mediaScocial.services.userService;

@RestController

public class messageController {

    @Autowired
    private messgaeService messageService;

    @Autowired
    private userService userService;

    @PostMapping("/api/messages/{chatId}")
    public Message sendMessage(@RequestHeader("Authorization") String token,
            @RequestBody Message message,
            @PathVariable("chatId") Integer chatId)
            throws Exception {
        System.out.println("message Is Printing" + message);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        try {
            User reqUser = userService.findUserByJwt(token);

            return messageService.sendMessage(reqUser, message, chatId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @GetMapping("/api/messages/{chatId}")
    public List<Message> findChatMessages(@PathVariable("chatId") Integer chatId)  {
        try {
            List<Message> messages = messageService.findChatMessages(chatId);

            return messages;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
