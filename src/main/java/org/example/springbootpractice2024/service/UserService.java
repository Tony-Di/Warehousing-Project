package org.example.springbootpractice2024.service;

import org.example.springbootpractice2024.dao.User;

import java.util.List;

public interface UserService {
    User getUserById(int id);
    List<User> getAllUsers();
    void saveUser(User user);
}
