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
    public ResponseEntity<Preparation> addItem(Long idPreparation, Long idItem, Double quantity){
        return preparationService.addItem(idPreparation, idItem, quantity);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteItem(Long id){
        return preparationService.deleteItem(id);
    }

    @GetMapping
    public ResponseEntity<Preparation> getPreparation(Long id){
        return preparationService.getPreparation();
    }


}
