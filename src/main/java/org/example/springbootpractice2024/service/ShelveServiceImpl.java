package org.example.springbootpractice2024.service;

import org.example.springbootpractice2024.dao.shelves;
import org.example.springbootpractice2024.dao.shelvesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShelveServiceImpl implements ShelveService {

    @Autowired
    private shelvesRepository repo;

    @Override
    public List<shelves> getAllShellves() {
        return repo.findAll();
    }

    @Override
    @Transactional
    public boolean updatequantity(int shelfId,String nameofgoods, int quantity) {
       shelves shelf = repo.findByshelvesIdAndNameofgoods(shelfId,nameofgoods);
       if(shelf==null)
       {
           return false;
       }
       else {
           shelf.setCountofgoods(quantity);
           repo.save(shelf);
           return true;
       }
    }
}
