package org.example.springbootpractice2024.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface shelvesRepository extends JpaRepository<shelves, Integer> {
    shelves findByshelvesIdAndNameofgoods(int shelvesId,String nameofgoods);
}
