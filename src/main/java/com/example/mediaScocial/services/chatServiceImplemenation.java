package com.example.mediaScocial.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mediaScocial.Enity.Chat;
import com.example.mediaScocial.Enity.User;
import com.example.mediaScocial.repositories.chatRepository;

@Service
public class chatServiceImplemenation implements chatService {

    @Autowired
    private chatRepository chatRepository;

    @Autowired
    private userService userService;

    @Override
    public Chat createChat(User reqUser, Integer UserId) throws Exception {

        User user = userService.findUserById(UserId);
        Chat isExist = chatRepository.findChatByUsersId(reqUser, user);

        if (isExist != null)
            return isExist;

        Chat chat = new Chat();
        chat.getUsers().add(user);
        chat.getUsers().add(reqUser);
        chat.setTimeStamp(new Date(System.currentTimeMillis()));
        Chat savedChat = chatRepository.save(chat);

        return savedChat;
    }

    @Override
    public Chat findChatById(Integer chatId) throws Exception {
        Optional<Chat> chat = chatRepository.findById(chatId);
        if (chat.isEmpty())
            throw new Exception("Chat not found with id : " + chatId);

        return chat.get();
    }

    @Override
    public List<Chat> findUsersChat(Integer UserId) {

        List<Chat> chats = chatRepository.findByUsersId(UserId);

        return chats;
    }

}
