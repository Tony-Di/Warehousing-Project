package org.example.springbootpractice2024.service;

import org.example.springbootpractice2024.dao.login;

public interface loginService {
    login getUserByName(String username);
}
