package com.zengroup.autopricerbackend.model;

import jakarta.persistence.*;


@Entity
@Table(name = "Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "commerce_id")
    private Commerce commerce;

    @ManyToOne
    @JoinColumn(name = "preparation_id")
    private Preparation preparation;

    public Integer getId() { return id; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Commerce getCommerce() { return commerce; }
}
