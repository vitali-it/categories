package com.category.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProduits() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product p) {
        return productRepository.save(p);
    }

    @Override
    public Product modifyProduct(Long id, Product modif) {
        Product p = productRepository.findById(id).orElseThrow();
        p.setNom(modif.getNom());
        p.setPrice(modif.getPrice());
        p.setQuantityStock(modif.getQuantityStock());
        return productRepository.save(p);
    }

    @Override
    public void removeProduct(Long id) {
        productRepository.deleteById(id);
    }
}
