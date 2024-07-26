package org.example.springbootpractice2024.dao;
import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "login")
public class login {
    @Id
    @Column(name = "login_id")
    @GeneratedValue(strategy = IDENTITY)
    private int loginid;

    @OneToOne
    @JoinColumn(name = "personal_information_id", referencedColumnName = "personal_information_id")
    private User userInformation;

    @Column(name ="username")
    private String username;

    @Column(name = "password")
    private String password;

    public int getlogin_id() {
        return loginid;
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

    public void setUserInformation(User userInformation) {
        this.userInformation = userInformation;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
