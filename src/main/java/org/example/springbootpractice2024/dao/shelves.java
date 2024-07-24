package org.example.springbootpractice2024.dao;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="shelves")
public class shelves {
    @Id
    @Column(name = "shelves_id")
    @GeneratedValue(strategy = IDENTITY)
    private int shelvesId;

    @Column(name ="name_of_goods")
    private String nameofgoods;

    @Column(name = "price_of_goods")
    private int priceofgoods;

    @Column(name = "count_of_goods")
    private int countofgoods;

    @Column(name = "location_of_shelves")
    private String locationofshelves;

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

    public void setCountofgoods(int countofgoods) {
        this.countofgoods = countofgoods;
    }
}
