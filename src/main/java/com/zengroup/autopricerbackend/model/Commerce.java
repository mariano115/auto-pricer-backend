package com.zengroup.autopricerbackend.model;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "Commerce")
public class Commerce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "commerce_id")
    private ArrayList<Preparation> preparationList;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "commerce_id")
    private ArrayList<Item> itemList;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Preparation> getPreparationList() {
        return preparationList;
    }

    public void setPreparationList(ArrayList<Preparation> preparationList) {
        this.preparationList = preparationList;
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }
}
