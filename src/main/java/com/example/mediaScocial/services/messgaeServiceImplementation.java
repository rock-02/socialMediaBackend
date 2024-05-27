package com.example.mediaScocial.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mediaScocial.Enity.Chat;
import com.example.mediaScocial.Enity.Message;
import com.example.mediaScocial.Enity.User;
import com.example.mediaScocial.repositories.chatRepository;
import com.example.mediaScocial.repositories.messageRepository;

@Service
public class messgaeServiceImplementation implements messgaeService {

    @Autowired
    private chatService chatService;

    @Autowired
    private chatRepository chatRepository;

    @Autowired
    private messageRepository messageRepository;

    @Override
    public Message sendMessage(User reqUser, Message message, Integer chatId) throws Exception {

        Chat chat = chatService.findChatById(chatId);

        if (chat == null) {
            throw new Exception("Chat not found");
        }

        Message newMessage = new Message();

        newMessage.setMessage(message.getMessage());

        newMessage.setImage(message.getImage());

        newMessage.setChat(chat);

        newMessage.setUser(reqUser);

        newMessage.setTimeStamp(new Date(System.currentTimeMillis()));

        chat.getMessages().add(newMessage);

        chatRepository.save(chat);

        Message savedMessage = messageRepository.save(newMessage);
        return savedMessage;
    }

    @Override
    public List<Message> findChatMessages(Integer chatId) throws Exception {
        Chat chat = chatService.findChatById(chatId);

        return chat.getMessages();
    }

    @Override
    public List<Message> findUserMessages(Integer userId) {

        throw new UnsupportedOperationException("Unimplemented method 'findUserMessages'");
    }

}
