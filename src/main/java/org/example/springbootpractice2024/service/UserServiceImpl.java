package org.example.springbootpractice2024.service;

import org.example.springbootpractice2024.dao.User;
import org.example.springbootpractice2024.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(int id) {
        User user = userRepository.findBypersonalinformationid(id);
        if(user == null)
        {
            return null;
        }
        else
        {
            return userRepository.findBypersonalinformationid(id);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
