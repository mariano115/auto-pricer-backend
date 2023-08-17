package com.zengroup.autopricerbackend.service;

import com.zengroup.autopricerbackend.model.Item;
import com.zengroup.autopricerbackend.model.Preparation;
import com.zengroup.autopricerbackend.repository.ItemRepository;
import com.zengroup.autopricerbackend.repository.PreparationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

public class PreparationService {

    @Autowired
    private PreparationRepository preparationRepository;

    @Autowired
    private ItemService itemService;

    public ResponseEntity<Preparation> addItem(Long id, Long idItem, Double quantity) {
        try {
        Preparation preparation = preparationRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        Item itemToAdd = itemService.getItemObjectById(idItem).
                orElseThrow(() -> new EntityNotFoundException(String.valueOf(idItem)));
        ArrayList<Item> items = preparation.getItemList();
        items.add(itemToAdd);
        preparation.setItemList(items);
        preparationRepository.save(preparation);
        return ResponseEntity.ok(preparation);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    public ResponseEntity<String> deleteItem(Long id) {
        return null;
    }

    public ResponseEntity<Preparation> getPreparation() {
        return null;
    }
}
