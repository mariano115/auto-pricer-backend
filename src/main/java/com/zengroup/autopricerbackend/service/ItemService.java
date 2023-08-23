package com.zengroup.autopricerbackend.service;


import com.zengroup.autopricerbackend.model.Item;
import com.zengroup.autopricerbackend.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public ResponseEntity<Item> saveItem(@RequestBody Item item) {
        Item newItem = itemRepository.save(item);
        return ResponseEntity.ok(newItem);
    }

    public ResponseEntity<List<Item>> fetchAllItems() {
        return ResponseEntity.ok(itemRepository.findAll());
    }

    public ResponseEntity<Optional<Item>> fetchItemById(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public Optional<Item> getItemObjectById(Long id) {
        return itemRepository.findById(id);
    }

    public ResponseEntity<Item> updateItem(Long id, Item item) {
        if (id == null) throw new IllegalArgumentException("ID cannot be null");
        Item itemToModify = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        itemToModify.setDescription(item.getDescription());
        itemToModify.setPrice(item.getPrice());
        Item itemModified = itemRepository.save(itemToModify);
        return ResponseEntity.ok(itemModified);
    }

    public ResponseEntity<String> deleteItem(Long id) {
        itemRepository.deleteById(id);
        return ResponseEntity.ok("Item Deleted Successfully");
    }
}
