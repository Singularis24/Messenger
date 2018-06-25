package com.singularis.messenger.service;

import com.singularis.messenger.domain.Dialog;
import com.singularis.messenger.domain.Message;

public interface DialogService {
    Dialog findById(int id);
    Dialog addDialog(Dialog dialog);
    Dialog updateDialog(Dialog dialog, Message message);
}
