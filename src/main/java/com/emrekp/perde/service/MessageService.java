package com.emrekp.perde.service;

import com.emrekp.perde.model.Message;
import com.emrekp.perde.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private MessageRepository repository;

    @Autowired
    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public boolean sendMessage(Message message) {
        try {
            repository.save(message); // TODO improve error check
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }
}
