package com.category.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category c, Long parentId) {
        if (parentId != null) {
            Category parent = categoryRepository.findById(parentId).orElseThrow();
            c.setParent(parent);
        }
        return categoryRepository.save(c);
    }

    @Override
    public Category modifyCategory(Long id, Category modif) {
        Category c = categoryRepository.findById(id).orElseThrow();
        c.setName(modif.getName());
        c.setDescription(modif.getDescription());
        return categoryRepository.save(c);
    }

    @Override
    public void removeCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void link(Long categoryId, Long productId) {
        Category c = categoryRepository.findById(categoryId).orElseThrow();
        Product p = productRepository.findById(productId).orElseThrow();
        c.getProducts().add(p);
        categoryRepository.save(c);
    }

    @Override
    public void unlink(Long categoryId, Long productId) {
        Category c = categoryRepository.findById(categoryId).orElseThrow();
        Product p = productRepository.findById(productId).orElseThrow();
        c.getProducts().remove(p);
        categoryRepository.save(c);
    }
}
