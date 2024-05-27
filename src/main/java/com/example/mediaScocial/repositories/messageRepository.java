package com.example.mediaScocial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.mediaScocial.Enity.Message;

public interface messageRepository extends JpaRepository<Message, Integer> {

    public List<Message> findByChatId(Integer chatId);

    public List<Message> findByUserId(Integer userId);

    
}
