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

    @Override
    @Transactional
    public boolean updateShelfdatails(int shelfId, String nameofgoods, int priceofgoods,String locationofshelf,String newnameofgoods) {
        if(nameofgoods.equals("null"))
        {
            List<shelves> shelveslist = repo.findByshelvesId(shelfId);
            for(int i =0;i<shelveslist.size();i++)
            {
                if(shelveslist.get(i).getName_of_goods() == null)
                {
                    shelves shelf = shelveslist.get(i);
                    shelf.setNameofgoods(newnameofgoods);
                    shelf.setPriceofgoods(priceofgoods);
                    shelf.setLocationofshelves(repo.findByshelvesId(shelfId).get(0).getLocation_of_shelves());
                    shelf.setShelvesId(shelfId);
                    repo.save(shelf);
                }
            }
            return true;
        }
        shelves shelf = repo.findByshelvesIdAndNameofgoods(shelfId,nameofgoods);
        if(shelf==null)
        {
            return false;
        }
        else {
            if(!nameofgoods.isEmpty())
            {
                shelf.setNameofgoods(newnameofgoods);
            }
            if(!locationofshelf.isEmpty())
            {
                shelf.setLocationofshelves(locationofshelf);
            }
            shelf.setPriceofgoods(priceofgoods);
            repo.save(shelf);
            return true;
        }
    }

    @Override
    @Transactional
    public boolean updateShelfGoods(int shelfId, String nameofgoods, int priceofgoods) {
        shelves shelf = repo.findByshelvesIdAndNameofgoods(shelfId,nameofgoods);
        if(shelf==null)
        {
            // If no such good exists, create a new record
            shelf = new shelves();
            shelf.setShelvesId(shelfId);
            shelf.setNameofgoods(nameofgoods);
            shelf.setPriceofgoods(priceofgoods);
            List<shelves> s = (List<shelves>) repo.findByshelvesId(shelfId);
            shelf.setLocationofshelves(s.get(0).getLocation_of_shelves());
        }
        else {
            if(!nameofgoods.isEmpty())
            {
                shelf.setNameofgoods(nameofgoods);
            }
            shelf.setPriceofgoods(priceofgoods);
        }
        repo.save(shelf);
        return true;
    }
    public int getNextShelvesId() {
        Integer maxShelvesId = repo.findMaxShelvesId();
        return (maxShelvesId != null ? maxShelvesId + 1 : 1);
    }
    @Override
    @Transactional
    public void saveShelf(shelves shelf) {
        shelf.setShelvesId(getNextShelvesId());
        shelf.setNameofgoods(null);
        repo.save(shelf);
    }
}
