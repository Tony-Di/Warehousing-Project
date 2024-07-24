package org.example.springbootpractice2024.controller;

import org.example.springbootpractice2024.dao.User;
import org.example.springbootpractice2024.service.UserService;
import org.example.springbootpractice2024.service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
public class usercontroller {
    @Autowired
    private loginService LoginService;

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public List<String> Hello() {
        System.out.println("Received form data: ");
        return List.of("hello", "world", "what");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody FormData formData) {
        System.out.println("Received form data: " + "user password:" + formData.password + "username: " + formData.username);

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
            String redirectUrl;
            if(formData.username.equals("admin"))
            {
                redirectUrl = "/admin";
            }
            else {
                redirectUrl = "/userpage/" + LoginService.getUserByName(formData.username).getlogin_id();

            }
            return ResponseEntity.ok().body("{\"message\":\"Form data received successfully!\", \"redirect\":\"" + redirectUrl + "\"}");
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
