package com.zengroup.autopricerbackend.controller;

import com.zengroup.autopricerbackend.model.Item;
import com.zengroup.autopricerbackend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> saveItem(@RequestBody Item item){
        return itemService.saveItem(item);
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems(){
        return itemService.fetchAllItems();
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Optional<Item>> getProductById(@PathVariable Long id){
        return itemService.fetchItemById(id);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<Item> updateProduct(@PathVariable Long id, @RequestBody Item item){
        return itemService.updateItem(id, item);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id){
        return itemService.deleteItem(id);
    }
}
