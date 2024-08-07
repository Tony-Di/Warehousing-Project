package org.example.springbootpractice2024.dao;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;
@Entity
@Table(name = "role")
public class role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(name ="role")
    private String role;

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
