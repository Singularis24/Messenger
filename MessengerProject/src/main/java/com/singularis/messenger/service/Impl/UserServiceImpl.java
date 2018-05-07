package com.singularis.messenger.service.Impl;

import com.singularis.messenger.domain.User;
import com.singularis.messenger.repository.UserRepository;
import com.singularis.messenger.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User addUser(User user) {
       return userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);

    }

    @Transactional
    @Override
    public User updateUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Transactional
    @Override
    public User getByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Transactional
    @Override
    public List<com.singularis.messenger.domain.User> getAllUsers() {
        return userRepository.findAll();
    }
}
