package com.example.mediaScocial.services;

import java.util.List;
import com.example.mediaScocial.Enity.Message;
import com.example.mediaScocial.Enity.User;

public interface messgaeService {

    public Message sendMessage(User reqUser, Message message, Integer chatId) throws Exception;

    public List<Message> findChatMessages(Integer chatId) throws Exception;

    public List<Message> findUserMessages(Integer userId);

}
