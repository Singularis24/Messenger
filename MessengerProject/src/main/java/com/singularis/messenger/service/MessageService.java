package com.singularis.messenger.service;

import com.singularis.messenger.domain.Message;

public interface MessageService {
    Message getById(int id);
    Message addMessage(Message message);
}
