package com.singularis.messenger.service;

import com.singularis.messenger.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    User addUser(User user);
    User updateUser(User user);
    void deleteUser(int id);
    User getByLogin (String login);
    List<User> getAllUsers();
}
