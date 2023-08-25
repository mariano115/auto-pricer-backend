package com.zengroup.autopricerbackend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Commerce")
public class Commerce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "commerce")
    private List<Preparation> preparations;

    @OneToMany(mappedBy = "commerce")
    private List<Item> items;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Preparation> getPreparations() {
        return preparations;
    }

    public void setPreparations(List<Preparation> preparations) {
        this.preparations = preparations;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
