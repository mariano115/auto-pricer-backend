package com.zengroup.autopricerbackend.service;

import com.zengroup.autopricerbackend.model.Commerce;
import com.zengroup.autopricerbackend.model.Preparation;
import com.zengroup.autopricerbackend.model.User;
import com.zengroup.autopricerbackend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<List<User>> fetchAllUser() {
        return ResponseEntity.ok(userRepository.findAll());

    }

    public ResponseEntity<User> saveUser(User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }

    public ResponseEntity<Optional<User>> fetchUserById(Integer id) {
        if (id == null) throw new IllegalArgumentException("ID cannot be null");
        return ResponseEntity.ok(userRepository.findById(id));
    }

    public ResponseEntity<User> updateUser(Integer id, User user) {
        if (id == null) throw new IllegalArgumentException("ID cannot be null");
        User userToModify = userRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        userToModify.setName(user.getName());
        userToModify.setCommerces(user.getCommerces());
        return ResponseEntity.ok(userRepository.save(userToModify));
    }

    public ResponseEntity<String> deleteUserById(Integer id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("User Deleted Successfully");
    }

    public ResponseEntity<User> addCommerce(Integer id, Commerce commerce) {
        if (id == null) throw new IllegalArgumentException("ID cannot be null");
        User user = userRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("User not found"));
        List<Commerce> commerces = user.getCommerces();
        commerces.add(commerce);
        user.setCommerces(commerces);
        return ResponseEntity.ok(userRepository.save(user));
    }

    public ResponseEntity<User> deleteCommerce(Integer id, Integer commerceId) {
        if (id == null) throw new IllegalArgumentException("ID cannot be null");
        User user = userRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("User not found"));
        List<Commerce> commerces = user.getCommerces();
        commerces.removeIf(commerce -> commerce.getId() == commerceId);
        user.setCommerces(commerces);
        return ResponseEntity.ok(userRepository.save(user));
    }
}
