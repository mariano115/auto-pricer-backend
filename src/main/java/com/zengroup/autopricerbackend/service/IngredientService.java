package com.zengroup.autopricerbackend.service;


import com.zengroup.autopricerbackend.model.Ingredient;
import com.zengroup.autopricerbackend.repository.IngredientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public ResponseEntity<Ingredient> saveIngredient(@RequestBody Ingredient ingredient) {
        Ingredient newIngredient = ingredientRepository.save(ingredient);
        return ResponseEntity.ok(newIngredient);
    }

    public ResponseEntity<List<Ingredient>> fetchAllIngredients() {
        return ResponseEntity.ok(ingredientRepository.findAll());
    }

    public ResponseEntity<Optional<Ingredient>> fetchIngredientById(Integer id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        if (ingredient.isPresent()) {
            return ResponseEntity.ok(ingredient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public Optional<Ingredient> getIngredientObjectById(Integer id) {
        return ingredientRepository.findById(id);
    }

    public ResponseEntity<Ingredient> updateIngredient(Integer id, Ingredient ingredient) {
        if (id == null) throw new IllegalArgumentException("ID cannot be null");
        Ingredient ingredientToModify = ingredientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        ingredientToModify.setDescription(ingredient.getDescription());
        ingredientToModify.setPrice(ingredient.getPrice());
        Ingredient ingredientModified = ingredientRepository.save(ingredientToModify);
        return ResponseEntity.ok(ingredientModified);
    }

    public ResponseEntity<String> deleteIngredient(Integer id) {
        ingredientRepository.deleteById(id);
        return ResponseEntity.ok("Ingredient Deleted Successfully");
    }
}
