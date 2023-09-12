package com.zengroup.autopricerbackend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Preparations")
public class Preparation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "preparation")
    private List<IngredientAmount> ingredientAmounts;

    @ManyToOne
    @JoinColumn(name = "commerce_id", nullable=false)
    private Commerce commerce;

    public Integer getId() { return id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<IngredientAmount> getIngredientAmounts() {
        return ingredientAmounts;
    }

    public void setIngredientAmounts(List<IngredientAmount> ingredientAmounts) {
        this.ingredientAmounts = ingredientAmounts;
    }

    public Commerce getCommerce() {
        return commerce;
    }
}
