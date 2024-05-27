package com.example.mediaScocial.services;

import java.util.*;
import com.example.mediaScocial.Enity.Chat;
import com.example.mediaScocial.Enity.User;

public interface chatService {

    public Chat createChat(User reqUser, Integer UserId) throws Exception;

    public Chat findChatById(Integer chatId) throws Exception;

    public List<Chat> findUsersChat(Integer UserId);
}
