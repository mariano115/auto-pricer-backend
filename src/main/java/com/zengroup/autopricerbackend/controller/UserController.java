package com.zengroup.autopricerbackend.controller;

import com.zengroup.autopricerbackend.model.Commerce;
import com.zengroup.autopricerbackend.model.Ingredient;
import com.zengroup.autopricerbackend.model.Preparation;
import com.zengroup.autopricerbackend.model.User;
import com.zengroup.autopricerbackend.service.CommerceService;
import com.zengroup.autopricerbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> fetchAllUser() { return userService.fetchAllUser(); }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping(("/{id}"))
    public ResponseEntity<Optional<User>> fetchUserById(@PathVariable Integer id) {
        return userService.fetchUserById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer id) {
        return userService.deleteUserById(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<User> addCommerce(@PathVariable Integer id, @RequestBody Commerce commerce){
        return userService.addCommerce(id, commerce);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteCommerce(@PathVariable Integer id, @RequestBody Integer commerceId){
        return userService.deleteCommerce(id, commerceId);
    }
}
