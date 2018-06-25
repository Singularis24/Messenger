package com.singularis.messenger.service;

import com.singularis.messenger.domain.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    User updateUser(User user);
    void deleteUser(int id);
    User findByLogin(String login);
    User findById(int id);
    List<User> getAllUsers();
    List<User> findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
    String findAvatarByIdUser(int id);
}
