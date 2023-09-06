package com.zengroup.autopricerbackend.controller;

import com.zengroup.autopricerbackend.model.Ingredient;
import com.zengroup.autopricerbackend.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<Ingredient> saveIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.saveIngredient(ingredient);
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> fetchAllIngredients() {
        return ingredientService.fetchAllIngredients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Ingredient>> fetchIngredientById(@PathVariable Integer id) {
        return ingredientService.fetchIngredientById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable Integer id, @RequestBody Ingredient ingredient) {
        return ingredientService.updateIngredient(id, ingredient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIngredient(@PathVariable Integer id) {
        return ingredientService.deleteIngredient(id);
    }
}
