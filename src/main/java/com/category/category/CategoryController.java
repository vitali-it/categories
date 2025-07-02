package com.category.category;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public List<Category> getAll() {
        return service.getAllCategories();
    }

    @PostMapping
    public Category create(@RequestBody Category c, @RequestParam(required = false) Long parentId) {
        return service.createCategory(c, parentId);
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody Category c) {
        return service.modifyCategory(id, c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.removeCategory(id);
    }

    @PostMapping("/{categoryId}/products/{productId}")
    public void link(@PathVariable Long categoryId, @PathVariable Long productId) {
        service.link(categoryId, productId);
    }

    @DeleteMapping("/{categoryId}/products/{productId}")
    public void unlink(@PathVariable Long categoryId, @PathVariable Long productId) {
        service.unlink(categoryId, productId);
    }
}
