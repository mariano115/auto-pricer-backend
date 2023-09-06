package com.zengroup.autopricerbackend.service;

import com.zengroup.autopricerbackend.model.Ingredient;
import com.zengroup.autopricerbackend.model.IngredientAmount;
import com.zengroup.autopricerbackend.model.Preparation;
import com.zengroup.autopricerbackend.repository.PreparationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PreparationService {

    @Autowired
    private PreparationRepository preparationRepository;

    @Autowired
    private IngredientService ingredientService;

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
        if (idPreparation == null) throw new IllegalArgumentException("ID cannot be null");
        Preparation preparationToModify = preparationRepository.findById(idPreparation)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(idPreparation)));
        preparationToModify.setName(preparation.getName());
        preparationToModify.setDescription(preparation.getDescription());
        preparationToModify.setIngredientAmounts(preparation.getIngredientAmounts());
        Preparation preparationModified = preparationRepository.save(preparationToModify);
        return ResponseEntity.ok(preparationModified);
    }

    public ResponseEntity<String> deletePreparation(Integer idPreparation) {
        if (idPreparation == null) throw new IllegalArgumentException("ID cannot be null");
        preparationRepository.deleteById(idPreparation);
        return ResponseEntity.ok("Preparation Deleted Successfully");
    }

    /*public ResponseEntity<Preparation> addIngredient(Integer id, Integer idIngredient, Double quantity) {
        try {
            Preparation preparation = preparationRepository.findById(id).
                    orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
            Ingredient ingredientToAdd = ingredientService.get(idIngredient).
                    orElseThrow(() -> new EntityNotFoundException(String.valueOf(idIngredient)));
            List<IngredientAmount> ingredientsAmounts = preparation.getIngredientAmounts();
            ingredientsAmounts.add(ingredientToAdd);
            preparation.setIngredientAmounts(ingredientsAmounts);
            preparationRepository.save(preparation);
            return ResponseEntity.ok(preparation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }*/

    /*public ResponseEntity<Preparation> deleteIngredient(Integer idPreparation, Integer idIngredient) {
        Preparation preparation = preparationRepository.findById(idPreparation)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(idPreparation)));
        List<Ingredient> ingredientsListToModify = preparation.getIngredients().stream()
                .filter(ingredient -> !Objects.equals(ingredient.getId(), idIngredient)).collect(Collectors.toList());
        preparation.setIngredients(ingredientsListToModify);
        return ResponseEntity.ok(preparation);
    }*/

}
