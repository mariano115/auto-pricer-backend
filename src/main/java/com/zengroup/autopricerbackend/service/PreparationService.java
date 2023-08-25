package com.zengroup.autopricerbackend.service;

import com.zengroup.autopricerbackend.model.Item;
import com.zengroup.autopricerbackend.model.Preparation;
import com.zengroup.autopricerbackend.repository.PreparationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PreparationService {

    @Autowired
    private PreparationRepository preparationRepository;

    @Autowired
    private ItemService itemService;

    public ResponseEntity<Preparation> getPreparation(Integer id) {
        Preparation preparation = preparationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        return ResponseEntity.ok(preparation);
    }
    public ResponseEntity<Preparation> createPreparation(Preparation preparation) {
        preparationRepository.save(preparation);
        return ResponseEntity.ok(preparation);
    }

    public ResponseEntity<Preparation> updatePreparation(Integer idPreparation, Preparation preparation) {
        if(idPreparation == null) throw new IllegalArgumentException("ID cannot be null");
        Preparation preparationToModify = preparationRepository.findById(idPreparation)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(idPreparation)));
        preparationToModify.setName(preparation.getName());
        preparationToModify.setDescription(preparation.getDescription());
        preparationToModify.setItems(preparation.getItems());
        Preparation preparationModified = preparationRepository.save(preparationToModify);
        return ResponseEntity.ok(preparationModified);
    }

    public ResponseEntity<String> deletePreparation(Integer idPreparation) {
        if(idPreparation == null) throw new IllegalArgumentException("ID cannot be null");
        preparationRepository.deleteById(idPreparation);
        return ResponseEntity.ok("Preparation Deleted Successfully");
    }
    public ResponseEntity<Preparation> addItem(Integer id, Integer idItem, Double quantity) {
        try {
        Preparation preparation = preparationRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        Item itemToAdd = itemService.getItemObjectById(idItem).
                orElseThrow(() -> new EntityNotFoundException(String.valueOf(idItem)));
        List<Item> items = preparation.getItems();
        items.add(itemToAdd);
        preparation.setItems(items);
        preparationRepository.save(preparation);
        return ResponseEntity.ok(preparation);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    public ResponseEntity<Preparation> deleteItem(Integer idPreparation, Integer idItem) {
        Preparation preparation = preparationRepository.findById(idPreparation)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(idPreparation)));
        List<Item> itemsListToModify = preparation.getItems().stream()
                .filter(item -> !Objects.equals(item.getId(), idItem)).collect(Collectors.toList());
        preparation.setItems(itemsListToModify);
        return ResponseEntity.ok(preparation);
    }

}
