package org.example.springbootpractice2024.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.example.springbootpractice2024.dao.User;
import org.example.springbootpractice2024.service.UserService;
import org.example.springbootpractice2024.service.loginService;
import org.example.springbootpractice2024.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class usercontroller {
    @Autowired
    private loginService LoginService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/hello")
    public List<String> Hello() {
        System.out.println("Received form data: ");
        return List.of("hello", "world", "what");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody FormData formData) {

        // check if the username exist in the databse
        if (LoginService.getUserByName(formData.username) == null) {

            return ResponseEntity.badRequest().body("User not found");
        }
        // check if the password matched
        else if (!LoginService.getUserByName(formData.username).getPassword().equals(formData.password)) {
            return ResponseEntity.badRequest().body("The password does not match");
        }
        // both matched then login and redirect to associated webpage with user information
        else {
            String position = LoginService.getUserByName(formData.username).getUserInformation().getPosition();
            String redirectUrl = determineRedirectUrl(position);

            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create()
                    .withSubject(formData.username)
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hour expiration
                    .withIssuedAt(new Date())
                    .withClaim("role", position) // Adding the role as a claim
                    .sign(algorithm);  // Signing the token with the specified algorithm

            return ResponseEntity.ok().body("{\"message\":\"Form data received successfully!\",\"token\":\"" + token + "\", \"redirect\":\"" + redirectUrl + "\"}");
        }
    }
    @PostMapping("/adduser")
    public ResponseEntity<String> adduser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok("User added successfully");
    }
    @GetMapping("/userpage/{id}")
    public ResponseEntity<String> getUserById(@PathVariable int id) {
        User userData = userService.getUserById(id);
        // if the user exist then return the associated information for the user
        if (userData != null) {
            return ResponseEntity.ok().body("{\"message\":\"Form data received successfully!\", \"name\":\"" + userData.getName() + "\"," +
                    "\"age\":\"" + userData.getAge() + "\", \"gender\":\"" + userData.getGender() + "\"," +
                    "\"position\":\"" + userData.getPosition() + "\"}");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    private String determineRedirectUrl(String position) {
        switch (position) {
            case "administration":
                return "/admin";
            case "stockinEmployee":
                return "/Stockin";
            case "stockoutEmployee":
                return "/Stockout";
            case "manager":
                return "/Manager";
            default:
                return "/";
        }
    }
    @GetMapping("/allusers")
    public ResponseEntity<List<User>> getAllusers() {
        List<User> usersList = userService.getAllUsers();
        return ResponseEntity.ok(usersList);
    }

    // class defined a form object with username and password
    private static class FormData {
        private String username;
        private String password;
        // getters and setters

        public String getPassword() {
            return password;
        }

        public String getUsername() {
            return username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
