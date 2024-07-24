package org.example.springbootpractice2024.controller;

import org.example.springbootpractice2024.dao.shelves;
import org.example.springbootpractice2024.service.ShelveService;
import org.example.springbootpractice2024.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
public class stockcontroller {

    @Autowired
    private ShelveService shelveService;

    @PostMapping("/stockin")
    public ResponseEntity<String> stockin(@RequestBody FormData formData) {
       return null;
    }

    @PostMapping("/stockout")
    public ResponseEntity<String> stockout(@RequestBody FormData formData) {
        return null;
    }

    @GetMapping("/instock")
    public ResponseEntity<List<shelves>> checkstock() {
        List<shelves> localdata = shelveService.getAllShellves();
        return ResponseEntity.ok(localdata);
    }

    @PostMapping("/increase-quantity/{shelvesId}/{nameofgoods}/{newquantity}")
    public ResponseEntity<String> updatequantity(@PathVariable int shelvesId, @PathVariable String nameofgoods,@PathVariable int newquantity) {
        try {
            boolean updated = shelveService.updatequantity(shelvesId, nameofgoods, newquantity);
            if (updated) {
                return ResponseEntity.ok("Quantity updated successfully");
            } else {
                // If updateQuantity returns false, implying the update could not be completed
                return ResponseEntity.badRequest().body("Update failed: the item could not be updated or did not exist.");
            }
        } catch (Exception e) {
            // Handle any exceptions thrown by the service layer
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    // class defined a form object with username and password
    private static class FormData {

    }
}
