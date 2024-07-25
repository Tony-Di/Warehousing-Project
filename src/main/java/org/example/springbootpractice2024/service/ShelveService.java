package org.example.springbootpractice2024.service;

import org.example.springbootpractice2024.dao.User;
import org.example.springbootpractice2024.dao.shelves;

import java.util.List;

public interface ShelveService {
    List<shelves> getAllShellves();
    boolean updatequantity(int shelfId,String nameofgoods, int quantity);
    boolean updateShelfdatails(int shelfId,String nameofgoods, int priceofgoods, String locationofshelf, String newnameofgoods);
    boolean updateShelfGoods(int shelfId,String nameofgoods, int priceofgoods);
    void saveShelf(shelves shelf);
}
