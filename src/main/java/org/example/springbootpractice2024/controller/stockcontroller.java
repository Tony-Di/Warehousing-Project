package org.example.springbootpractice2024.controller;

import org.example.springbootpractice2024.dao.shelves;
import org.example.springbootpractice2024.service.ShelveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Comparator;
import java.util.List;

@RestController
public class stockcontroller {

    @Autowired
    private ShelveService shelveService;

    @GetMapping("/instock")
    public ResponseEntity<List<shelves>> checkstock() {
        List<shelves> localdata = shelveService.getAllShellves();
        localdata.sort(Comparator.comparing(shelves::getId));
        return ResponseEntity.ok(localdata);
    }

    @PostMapping("/update-quantity/{shelvesId}/{nameofgoods}/{newquantity}")
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
    @PostMapping("/updateShelf/{shelvesID}/{nameofgoods}/{locationofshelves}/{priceofgoods}/{oringinalnameofgoods}")
    public ResponseEntity<String> updateShelfdetails(@PathVariable int shelvesID,@PathVariable String nameofgoods,@PathVariable int priceofgoods,@PathVariable String locationofshelves, @PathVariable String oringinalnameofgoods) {
        try {
            System.out.println(locationofshelves);
            boolean updated = shelveService.updateShelfdatails(shelvesID, oringinalnameofgoods,priceofgoods,locationofshelves,nameofgoods);
            if (updated) {
                return ResponseEntity.ok("shelf details updated successfully");
            } else {
                // If updateQuantity returns false, implying the update could not be completed
                return ResponseEntity.badRequest().body("Update failed: the item could not be updated or did not exist.");
            }
        } catch (Exception e) {
            // Handle any exceptions thrown by the service layer
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
    @PostMapping("/addshelf")
    public ResponseEntity<String> addshelf(@RequestBody shelves shelf) {
        shelveService.saveShelf(shelf);
        return ResponseEntity.ok("Shelf added successfully");
    }

    @PostMapping("/addgoods/{shelvesId}/{nameofgoods}/{priceofgoods}")
    public ResponseEntity<String> addgoods(@PathVariable int shelvesId, @PathVariable String nameofgoods, @PathVariable int priceofgoods) {
        shelveService.updateShelfGoods(shelvesId,nameofgoods,priceofgoods);
        return ResponseEntity.ok("Goods added successfully");
    }
//    @PostMapping("/checkToken/{token}")
//    public ResponseEntity<String> checkToken( @PathVariable String token) {
//
//    }

}
