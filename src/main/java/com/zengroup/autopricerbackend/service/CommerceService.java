package com.zengroup.autopricerbackend.service;

import com.zengroup.autopricerbackend.model.Commerce;
import com.zengroup.autopricerbackend.model.Preparation;
import com.zengroup.autopricerbackend.repository.CommerceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommerceService {
    @Autowired
    private CommerceRepository commerceRepository;

    public ResponseEntity<Optional<Commerce>> fetchCommerceById(Integer id) {
        if (id == null) throw new IllegalArgumentException("ID cannot be null");
        return ResponseEntity.ok(commerceRepository.findById(id));
    }

    public ResponseEntity<List<Commerce>> fetchAllItems() {
        return ResponseEntity.ok(commerceRepository.findAll());
    }


    public ResponseEntity<Commerce> saveCommerce(Commerce commerce) {
        Commerce newCommerce = commerceRepository.save(commerce);
        return ResponseEntity.ok(newCommerce);
    }


    public ResponseEntity<Commerce> updateCommerce(Integer id, Commerce commerce) {
        if (id == null) throw new IllegalArgumentException("ID cannot be null");
        Commerce commerceToModify = commerceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        commerceToModify.setName(commerce.getName());
        commerceToModify.setPreparations(commerce.getPreparations());
        commerceToModify.setItems(commerce.getItems());
        Commerce commerceModified = commerceRepository.save(commerceToModify);
        return ResponseEntity.ok(commerceModified);
    }

    public ResponseEntity<String> deleteCommerce(Integer id) {
        commerceRepository.deleteById(id);
        return ResponseEntity.ok("Commerce Deleted Successfully");
    }

    public ResponseEntity<String> addPreparation(Integer id, Preparation preparation) {
    }

    public ResponseEntity<String> deletePreparation(Integer id, Integer preparationId) {
    }
}
