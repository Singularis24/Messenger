package com.singularis.messenger.service.Impl;

import com.singularis.messenger.domain.Message;
import com.singularis.messenger.repository.DialogRepository;
import com.singularis.messenger.repository.MessageRepository;
import com.singularis.messenger.repository.UserRepository;
import com.singularis.messenger.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    DialogRepository dialogRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;

    @Transactional
    @Override
    public Message getById(int id) {
        return messageRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Message addMessage(Message message){
        return messageRepository.saveAndFlush(message);
    }
}
