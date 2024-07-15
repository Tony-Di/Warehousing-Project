package org.example.springbootpractice2024.service;

import org.example.springbootpractice2024.dao.User;
import org.example.springbootpractice2024.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow();
    }
}
