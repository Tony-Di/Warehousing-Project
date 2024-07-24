package org.example.springbootpractice2024.service;

import org.example.springbootpractice2024.dao.login;
import org.example.springbootpractice2024.dao.loginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class loginServiceImpl implements loginService{

    @Autowired
    private loginRepository loginRepo;

    @Override
    public login getUserByName(String username) {
        return loginRepo.findByusername(username);
    }
}
