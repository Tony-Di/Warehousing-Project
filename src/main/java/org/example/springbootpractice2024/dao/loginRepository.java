package org.example.springbootpractice2024.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface loginRepository extends JpaRepository<login, Integer> {
    login findByusername(String username);
}
