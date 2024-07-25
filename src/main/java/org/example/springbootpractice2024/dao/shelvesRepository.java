package org.example.springbootpractice2024.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface shelvesRepository extends JpaRepository<shelves, Integer> {
    shelves findByshelvesIdAndNameofgoods(int shelvesId,String nameofgoods);
    List<shelves> findByshelvesId(int shelvesId);
    @Query("SELECT MAX(s.shelvesId) FROM shelves s")
    Integer findMaxShelvesId();
}
