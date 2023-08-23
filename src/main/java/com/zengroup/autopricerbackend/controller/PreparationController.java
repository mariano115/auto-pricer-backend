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

    @GetMapping
    public ResponseEntity<Preparation> getPreparation(Long id){
        return preparationService.getPreparation(id);
    }

    @PostMapping
    public ResponseEntity<Preparation> createPreparation(Preparation preparation){
        return preparationService.createPreparation(preparation);
    }

    @PutMapping
    public ResponseEntity<Preparation> updatePreparation(Long idPreparation, Preparation preparation){
        return preparationService.updatePreparation(idPreparation, preparation);
    }

    @DeleteMapping
    public ResponseEntity<String> deletePreparation(Long idPreparation){
        return preparationService.deletePreparation(idPreparation);
    }


    @PostMapping
    public ResponseEntity<Preparation> addItem(Long idPreparation, Long idItem, Double quantity){
        return preparationService.addItem(idPreparation, idItem, quantity);
    }

    @DeleteMapping
    public ResponseEntity<Preparation> deleteItem(Long idPreparation, Long idItem){
        return preparationService.deleteItem(idPreparation, idItem);
    }


}
