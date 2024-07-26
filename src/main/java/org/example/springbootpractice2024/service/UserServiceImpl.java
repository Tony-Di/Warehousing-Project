package org.example.springbootpractice2024.service;

import org.example.springbootpractice2024.dao.User;
import org.example.springbootpractice2024.dao.login;
import org.example.springbootpractice2024.dao.UserRepository;
import org.example.springbootpractice2024.dao.loginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private loginRepository loginRepository;

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
        login newlogin = new login();
        newlogin.setUsername(user.getName());
        newlogin.setPassword(user.getName());
        newlogin.setUserInformation(user);
        loginRepository.save(newlogin);
    }
}
