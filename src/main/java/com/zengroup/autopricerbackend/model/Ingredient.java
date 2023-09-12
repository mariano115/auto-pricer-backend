package com.zengroup.autopricerbackend.model;

import jakarta.persistence.*;


@Entity
@Table(name = "Ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double presentation;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "commerce_id", nullable=false)
    private Commerce commerce;

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPresentation() {
        return presentation;
    }

    public void setPresentation(Double presentation) {
        this.presentation = presentation;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
