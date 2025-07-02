package com.category.category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    Category createCategory(Category c, Long parentId);

    Category modifyCategory(Long id, Category modif);

    void removeCategory(Long id);

    void link(Long categoryId, Long productId);

    void unlink(Long categoryId, Long productId);
}
