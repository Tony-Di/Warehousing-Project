package org.example.springbootpractice2024.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface roleRepository extends JpaRepository<role, Integer> {
    role findByRole(String role);
}
