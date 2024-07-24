package org.example.springbootpractice2024.dao;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="stockoutrecords")
public class stockoutrecords {
    @Id
    @Column(name = "recordId")
    @GeneratedValue(strategy = IDENTITY)
    private int recordId;

    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "personal_information_id")
    private User userInformation;

    @Column(name ="name_of_goods")
    private String nameofgoods;

    @Column(name = "quantity")
    private int quantity;

    @OneToOne
    @JoinColumn(name = "shelveId", referencedColumnName = "shelves_id")
    private shelves shelves;

    @Column(name = "Date")
    private LocalDateTime date;

    public User getUserInformation() {
        return userInformation;
    }

    public String getName_of_goods() {
        return nameofgoods;
    }

    public int getQuantity() {
        return quantity;
    }

    public shelves getShelves() {
        return shelves;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
