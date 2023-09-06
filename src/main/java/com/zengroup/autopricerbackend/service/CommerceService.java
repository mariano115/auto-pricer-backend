package com.zengroup.autopricerbackend.service;

import com.zengroup.autopricerbackend.model.Commerce;
import com.zengroup.autopricerbackend.model.Ingredient;
import com.zengroup.autopricerbackend.model.Preparation;
import com.zengroup.autopricerbackend.repository.CommerceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommerceService {
    @Autowired
    private CommerceRepository commerceRepository;

    public ResponseEntity<Optional<Commerce>> fetchCommerceById(Integer id) {
        if (id == null) throw new IllegalArgumentException("ID cannot be null");
        return ResponseEntity.ok(commerceRepository.findById(id));
    }

    public ResponseEntity<List<Commerce>> fetchAllCommerce() {
        return ResponseEntity.ok(commerceRepository.findAll());
    }


    public ResponseEntity<Commerce> saveCommerce(Commerce commerce) {
        Commerce newCommerce = commerceRepository.save(commerce);
        return ResponseEntity.ok(newCommerce);
    }


    public ResponseEntity<Commerce> updateCommerce(Integer id, Commerce commerce) {
        if (id == null) throw new IllegalArgumentException("ID cannot be null");
        Commerce commerceToModify = commerceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        commerceToModify.setName(commerce.getName());
        commerceToModify.setPreparations(commerce.getPreparations());
        commerceToModify.setIngredients(commerce.getIngredients());
        Commerce commerceModified = commerceRepository.save(commerceToModify);
        return ResponseEntity.ok(commerceModified);
    }

    public ResponseEntity<String> deleteCommerce(Integer id) {
        commerceRepository.deleteById(id);
        return ResponseEntity.ok("Commerce Deleted Successfully");
    }

    public ResponseEntity<String> addPreparation(Integer id, Preparation preparation) {
        Commerce commerce = commerceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        List<Preparation> preparations = commerce.getPreparations();
        Optional<Preparation> preparationFind = preparations.stream().filter(preparationOject -> preparation.getId().equals(preparationOject.getId())).findFirst();
        if (preparationFind.isPresent()) {
            throw new IllegalArgumentException("Preparation cannot be repeated");
        } else {
            preparations.add(preparation);
            commerce.setPreparations(preparations);
            commerceRepository.save(commerce);
            return ResponseEntity.ok("Preparation Added Successfully");
        }
    }

    public ResponseEntity<String> deletePreparation(Integer id, Integer preparationId) {
        Commerce commerce = commerceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        List<Preparation> preparations = commerce.getPreparations();
        List<Preparation> preparationsFiltered = preparations.stream().filter(preparationOject -> preparationId.equals(preparationOject.getId())).collect(Collectors.toList());
        commerce.setPreparations(preparationsFiltered);
        commerceRepository.save(commerce);
        return ResponseEntity.ok("Preparation Deleted Successfully");
    }

    public ResponseEntity<String> addIngredient(Integer id, Ingredient ingredient) {
        Commerce commerce = commerceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        List<Ingredient> ingredients = commerce.getIngredients();
        Optional<Ingredient> ingredientFind = ingredients.stream().filter(ingredientObject -> ingredient.getId() == ingredient.getId()).findFirst();
        if (ingredientFind.isPresent()) {
            throw new IllegalArgumentException("Preparation cannot be repeated");
        } else {
            ingredients.add(ingredient);
            commerce.setIngredients(ingredients);
            commerceRepository.save(commerce);
            return ResponseEntity.ok("Ingredient Added Successfully");
        }
    }

    public ResponseEntity<String> deleteIngredient(Integer id, Integer idIngredient) {
        Commerce commerce = commerceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        List<Ingredient> ingredients = commerce.getIngredients();
        List<Ingredient> ingredientsFiltered = ingredients.stream().filter(ingredientObject -> idIngredient.equals(ingredientObject.getId())).collect(Collectors.toList());
        commerce.setIngredients(ingredientsFiltered);
        commerceRepository.save(commerce);
        return ResponseEntity.ok("Ingredient Deleted Successfully");
    }
}
