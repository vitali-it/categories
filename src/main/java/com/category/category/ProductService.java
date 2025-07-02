package com.category.category;

import java.util.List;

public interface ProductService {

    List<Product> getAllProduits();

    Product createProduct(Product p);

    Product modifyProduct(Long id, Product modif);

    void removeProduct(Long id);
}
