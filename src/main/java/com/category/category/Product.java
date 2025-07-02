package com.category.category;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private Double price;
    private Integer quantityStock;

    @ManyToMany(mappedBy = "products")
    private Set<Category> categories = new HashSet<>();
}
