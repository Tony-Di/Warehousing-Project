package org.example.springbootpractice2024.dao;
import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "login")
public class login {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = IDENTITY)
    private int user_id;

    @OneToOne
    @JoinColumn(name = "user_info_id", referencedColumnName = "id")
    private User userInformation;

    @Column(name ="username")
    private String username;

    @Column(name = "password")
    private String password;

    public int getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public User getUserInformation() {
        return userInformation;
    }
}
