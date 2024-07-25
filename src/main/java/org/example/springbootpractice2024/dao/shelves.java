package org.example.springbootpractice2024.dao;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="shelves")
public class shelves {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(name = "shelves_id")
    private int shelvesId;

    @Column(name ="name_of_goods")
    private String nameofgoods;

    @Column(name = "price_of_goods")
    private int priceofgoods;

    @Column(name = "count_of_goods")
    private int countofgoods;

    @Column(name = "location_of_shelves")
    private String locationofshelves;

    public int getId() {
        return id;
    }

    public int getShelvesId() {
        return shelvesId;
    }

    public String getName_of_goods() {
        return nameofgoods;
    }

    public int getPrice_of_goods() {
        return priceofgoods;
    }

    public int getCount_of_goods() {
        return countofgoods;
    }

    public String getLocation_of_shelves() {
        return locationofshelves;
    }

    public void setShelvesId(int shelvesId) {
        this.shelvesId = shelvesId;
    }

    public void setCountofgoods(int countofgoods) {
        this.countofgoods = countofgoods;
    }

    public void setNameofgoods(String nameofgoods) {
        this.nameofgoods = nameofgoods;
    }

    public void setPriceofgoods(int priceofgoods) {
        this.priceofgoods = priceofgoods;
    }

    public void setLocationofshelves(String locationofshelves) {
        this.locationofshelves = locationofshelves;
    }
}
