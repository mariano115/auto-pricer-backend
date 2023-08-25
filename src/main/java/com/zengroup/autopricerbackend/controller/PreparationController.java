package com.zengroup.autopricerbackend.controller;

import com.zengroup.autopricerbackend.model.Preparation;
import com.zengroup.autopricerbackend.service.PreparationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/preparations")
public class PreparationController {

    @Autowired
    private PreparationService preparationService;

    @PostMapping
    public ResponseEntity<Preparation> createPreparation(Preparation preparation) {
        return preparationService.createPreparation(preparation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Preparation> getPreparation(@PathVariable Integer id) {
        return preparationService.getPreparation(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Preparation> updatePreparation(@PathVariable Integer id, @RequestBody Preparation preparation) {
        return preparationService.updatePreparation(id, preparation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePreparation(@PathVariable Integer id) {
        return preparationService.deletePreparation(id);
    }


    @PostMapping("/{id}")
    public ResponseEntity<Preparation> addItem(@PathVariable Integer id, @RequestBody Integer idItem, Double quantity) {
        return preparationService.addItem(id, idItem, quantity);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Preparation> deleteItem(Integer id, Integer idItem) {
        return preparationService.deleteItem(id, idItem);
    }


}
