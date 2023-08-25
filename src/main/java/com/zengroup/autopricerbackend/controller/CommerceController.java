package com.zengroup.autopricerbackend.controller;

import com.zengroup.autopricerbackend.model.Commerce;
import com.zengroup.autopricerbackend.service.CommerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/commerces")
public class CommerceController {

    @Autowired
    private CommerceService commerceService;

    @GetMapping
    public ResponseEntity<List<Commerce>> fetchAllCommerce() {
        return commerceService.fetchAllItems();
    }

    @PostMapping
    public ResponseEntity<Commerce> saveCommerce(@RequestBody Commerce commerce) {
        return commerceService.saveCommerce(commerce);
    }

    @GetMapping(("/commerces/{id}"))
    public ResponseEntity<Optional<Commerce>> fetchCommerceById(@PathVariable Integer id) {
        return commerceService.fetchCommerceById(id);
    }

    @PutMapping("/commerces/{id}")
    public ResponseEntity<Commerce> updateCommerce(@PathVariable Integer id, @RequestBody Commerce commerce) {
        return commerceService.updateCommerce(id, commerce);
    }

    @DeleteMapping("/commerces/{id}")
    public ResponseEntity<String> deleteCommerce(@PathVariable Integer id) {
        return commerceService.deleteCommerce(id);
    }
}
