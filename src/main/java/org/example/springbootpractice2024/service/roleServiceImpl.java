package org.example.springbootpractice2024.service;

import org.example.springbootpractice2024.dao.role;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.springbootpractice2024.dao.roleRepository;
import org.springframework.stereotype.Service;

@Service
public class roleServiceImpl implements RoleService {

    @Autowired
    private roleRepository roleRepository;

    @Override
    public role getRole(String roleName) {
        return roleRepository.findByRole(roleName);
    }
}
