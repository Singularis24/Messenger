package com.singularis.messenger.service.Impl;

import com.singularis.messenger.domain.Dialog;
import com.singularis.messenger.domain.Message;
import com.singularis.messenger.repository.DialogRepository;
import com.singularis.messenger.service.DialogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DialogServiceImpl implements DialogService {

    @Autowired
    DialogRepository dialogRepository;

    @Transactional
    @Override
    public Dialog findById(int id) {
        return dialogRepository.findById(id).get();
    }

    @Transactional
    @Override
    public Dialog addDialog(Dialog dialog) {
        return dialogRepository.saveAndFlush(dialog);
    }

    @Transactional
    @Override
    public Dialog updateDialog(Dialog dialog, Message message) {
        dialog.getMessages().add(message);
        return dialogRepository.saveAndFlush(dialog);
    }
}
